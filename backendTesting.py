#!/usr/bin/env python

import os, sys
import subprocess
import fnmatch

# CONSTANTS

compiler = "wacc_examples/refCompile"
root = "wacc_examples/valid"
ignores = ['Loop', 'loop', 'manyVariables']


# FUNCTIONS

def getReferenceOutput(cacheFile):
    system_command = "{0} -a -d {1}".format(compiler, root)
    if not os.path.exists(compiler) or not os.path.exists(root):
        print "The reference compiler or the root of your examples can not be found."
        exit(1)
    process = subprocess.Popen(system_command.split(), stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    output = process.communicate()[0].split('\n')
    if len(output) == 1 and len(output[0]) == 0:
        print "Couldn't get reference output. Either:"
        print "\t1: Thank Mark for using a silly system ruby instead of a local one."
        print "\t2: You're not on campus."
        print "\t3: You haven't got ruby installed at system level"
        print "\t4: You haven't got the rest-client installed"
        print "\t5: The earth might not exist..."
        exit(1)
    makeFileDirectory(cacheFile)
    with open(cacheFile, 'w') as f:
        f.write('\n'.join(output))
    return output


def cacheOutput():
    cacheFile = 'test_compile_output/cachedData.txt'
    if (os.path.exists(cacheFile)):
        with open(cacheFile, 'r') as f:
            data = f.read().split('\n')
            if (len(data) < 10):
                data = getReferenceOutput(cacheFile)
    else:
        data = getReferenceOutput(cacheFile)
    return data


def compile(file_in):
    file_out = 'test_compile_output/compiled_output' + file_in.split('wacc_examples')[1]
    file_out = file_out.replace('.wacc', '.s')
    makeFileDirectory(file_out)
    system_command = "./compile {0} {1}".format(file_in, file_out)
    process = subprocess.Popen(system_command.split(), stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    process.communicate()
    return file_out


def makeFileDirectory(file_path):
    file_dirs = file_path.split('/')
    i = 0
    while (i < len(file_dirs) - 1):
        current_dir = "/".join(file_dirs[:i + 1])
        if not os.path.exists(current_dir):
            os.mkdir(current_dir)
        i += 1


def getDataFromOutput(output):
    data = []
    i = 0
    while (i < len(output)):
        if "calling the reference compiler on" in output[i]:
            filename = output[i][34:]  # Gets the text after the above

            if len(output) == i + 1:
                return []

            # Get expected output
            while 'Output:' not in output[i - 1]:
                i += 1
                if len(output) == i + 1:
                    return []
            output_exec = []
            while '# ' in output[i]:
                output_exec.append(output[i][2:])
                i += 1
                if len(output) == i + 1:
                    return []

            # Get expected assembly
            separator = '==========================================================='
            while separator not in output[i]:
                i += 1
                if len(output) == i + 1:
                    return []
            assembly = []
            i += 1
            while (separator not in output[i]):
                assembly.append('\t'.join(output[i].split('\t')[1:]))
                i += 1
                if len(output) == i + 1:
                    return []
            data.append((filename.replace('wacc', 's'), '\n'.join(assembly), '\n'.join(output_exec)))
        i += 1
    return data


def writeData(data):
    for i in data:
        data_path = "test_compile_output/{0}".format(i[0])
        makeFileDirectory(data_path)
        with open(data_path, 'w') as f:
            f.write(i[1])


def compareFiles(file_1, file_2):
    with open(file_1, 'r') as f1:
        with open(file_2, 'r') as f2:
            return f1.read() == f2.read()


def runFile(file_in, file_out):
    system_command = "arm-linux-gnueabi-gcc -o {1} -mcpu=arm1176jzf-s -mtune=arm1176jzf-s {0}".format(file_in, file_out)
    process = subprocess.Popen(system_command.split(), stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    output = process.communicate()
    if output[1] != '':
        raise IOError
    system_command = "qemu-arm -L /usr/arm-linux-gnueabi/ {0}".format(file_out)
    process = subprocess.Popen(system_command.split(), stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    output = process.communicate()
    if output[1] != '':
        raise IOError
    os.remove(file_out)
    with open(file_out, 'w') as f:
        f.write(output[0])
    return file_out


def show_error(file_1_orig, file1, file2):
    print '====================================='
    print '{0} has a compile error:\n'.format(file_1_orig.split('/')[-1])
    system_command = "diff {0} {1}".format(file1, file2)
    process = subprocess.Popen(system_command.split(), stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    print process.communicate()[0]


def show_output_error(file_1_orig, file1, file2):
    print '====================================='
    print '{0} has an output error:\n'.format(file_1_orig.split('/')[-1])
    outputfile1 = 'output1.txt'
    outputfile2 = 'output2.txt'
    system_command = "diff {0} {1}".format(runFile(file1, outputfile1), runFile(file2, outputfile2))
    os.remove(outputfile1)
    os.remove(outputfile2)
    process = subprocess.Popen(system_command.split(), stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    print process.communicate()[0]


def validateOutput(file1, file2):
    system_command = "which arm-linux-gnueabi-gcc"
    process = subprocess.Popen(system_command.split(), stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    if 'not found' in process.communicate()[0]:
        raise IOError
    system_command = "which qemu-arm"
    process = subprocess.Popen(system_command.split(), stdout=subprocess.PIPE, stderr=subprocess.PIPE)
    if 'not found' in process.communicate()[0] or not os.path.exists('/usr/arm-linux-gnueabi'):
        raise IOError
    output1 = 'output1.txt'
    output2 = 'output2.txt'
    runFile(file1, output1)
    runFile(file2, output2)
    with open(output1, 'r') as f1:
        with open(output2, 'r') as f2:
            valid = f1.read() == f2.read()
    try:
        os.remove(output1)
    except IOError:
        valid = valid
    try:
        os.remove(output2)
    except IOError:
        valid = valid
    return valid


def verify_file(root, filename):
    compile_file = os.path.join(root, filename)
    compiled_file = compile(compile_file)
    referenceOutput = compiled_file.replace('compiled_output', 's_examples')
    correct = True
    try:
        if not compareFiles(compiled_file, referenceOutput):
            try:
                if not validateOutput(compiled_file, referenceOutput):
                    show_output_error(filename, compile_file, referenceOutput)
            except IOError:
                show_error(filename, compiled_file, referenceOutput)
                correct = False
    except IOError:
        correct = False
    return correct


def printResult(verified, incorrect):
    print '====================================='
    print '====================================='
    print 'Verified:\t{0}'.format(verified)
    print 'Correct:\t{0}'.format(verified - incorrect)
    print 'Incorrect:\t{0}'.format(incorrect)


# MAIN

output = cacheOutput()
data = getDataFromOutput(output)
writeData(data)

verified = 0
incorrect = 0

root = 'wacc_examples/valid'

if len(sys.argv) > 1:
    path = sys.argv[1]
    if '.wacc' in path:
        for i in ignores:
            if i in path:
                exit(0)
        verified += 1
        if not verify_file('/'.join(path.split('/')[:-1]), path.split('/')[-1]):
            incorrect += 1
        printResult(verified, incorrect)
        exit(0)
    else:
        root = path

for root, dirnames, filenames in os.walk(root):
    for filename in fnmatch.filter(filenames, '*.wacc'):
        print 'Running verify on:\t{0}'.format(filename)
        breaker = False
        for i in ignores:
            if i in filename:
                breaker = True
                break
        if breaker: continue
        verified += 1
        if not verify_file(root, filename):
            incorrect += 1

printResult(verified, incorrect)
