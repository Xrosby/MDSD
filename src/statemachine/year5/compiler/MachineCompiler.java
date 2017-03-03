/*
Copyright (c) 2017, Ulrik Pagh Schultz, University of Southern Denmark
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met: 

1. Redistributions of source code must retain the above copyright notice, this
   list of conditions and the following disclaimer. 
2. Redistributions in binary form must reproduce the above copyright notice,
   this list of conditions and the following disclaimer in the documentation
   and/or other materials provided with the distribution. 

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

The views and conclusions contained in the software and documentation are those
of the authors and should not be interpreted as representing official policies, 
either expressed or implied, of the University of Southern Denmark.
*/
package statemachine.year5.compiler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import statemachine.year4.codegen.MachineGenerator;

/**
 * Class that provides a single entrypoint for a compilation process from statemachine source code
 * to generated code
 * @author ups
 *
 */
public class MachineCompiler {

	/**
	 * Compile statemachine source code to generated code
	 * @param inputFileName the input file to parse
	 * @param outputDirectory the base directory in which to store the generated code
	 * @throws FileNotFoundException if the input file was not found or if the target directory does not exist
	 */
	public static void compile(String inputFileName, String outputDirectory) throws FileNotFoundException {
		FileReader file = new FileReader(inputFileName);
		MachineParser parser = new MachineParser(new BufferedReader(file));
		MachineGenerator generator = new MachineGenerator(parser);
		String name = parser.getMachineName();
		String className = getClassName(name);
		String packageName = getPackageName(name);
		generator.compile(outputDirectory, packageName, className);
	}

	/**
	 * Get the package name from a dotted name sequence
	 * @param name full name
	 * @return the package name
	 */
	private static String getPackageName(String name) {
		int index = name.lastIndexOf('.');
		if(index==-1) throw new Error("Illegal statemachine name: "+name);
		return name.substring(0,index);
	}

	/**
	 * Get the class name from a dotted name sequence
	 * @param name full name
	 * @return the class name
	 */
	private static String getClassName(String name) {
		int index = name.lastIndexOf('.');
		if(index==-1 || index>=name.length()-2) throw new Error("Illegal statemachine name: "+name);
		return name.substring(index+1);
	}
	
}
