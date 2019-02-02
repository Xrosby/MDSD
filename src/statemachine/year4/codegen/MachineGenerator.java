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
package statemachine.year4.codegen;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import statemachine.year2.framework.Transition;
import statemachine.year3.dsl.FluentMachine;
import statemachine.year3.dsl.FluentMachine.TransitionFactory;
import statemachine.year3.dsl.GenericRuntimeState;
import statemachine.year3.dsl.FluentMachine.Condition;
import statemachine.year3.dsl.FluentMachine.Effect;

/**
 * Manages compilation of a FluentMachine model to a class file
 * @author ups
 */
public class MachineGenerator {

	/**
	 * The model to compile
	 */
	private FluentMachine machine;

	/**
	 * Create compiler for the given FluentMachine model
	 * @param machine the model to compile
	 */
	public MachineGenerator(FluentMachine machine) {
		machine.setTransitionFactory(new HolderFactory());
		this.machine = machine;
	}
	
	/**
	 * Perform the compilation into the given directory with the given naming
	 * @param directory name of directory to store code in, excluding package-name-derived directory
	 * @param packageName package (and subdirectory) to store code in
	 * @param className class name and also file name to store code in
	 */
	public void compile(String directory, String packageName, String className) {
		String definition = new MachineJavaCodeGenerator(machine).generate(packageName, className);
		String fileName = directory+File.separatorChar+packageName.replace('.', File.separatorChar)+File.separatorChar+className+".java";
		try {
			FileWriter writer = new FileWriter(fileName);
			writer.write(definition);
			writer.close();
		} catch (IOException e) {
			throw new Error("I/O error: "+e);
		}
		System.out.println("Wrote file "+fileName);
	}
	
	/**
	 * Transition factory that stores the transition in a TransitionHolder instance
	 */
	private class HolderFactory extends TransitionFactory {
		@Override
		protected Transition<GenericRuntimeState> createTransitionHook(String target, 
				Effect effect, String effectVar, int effectArg, 
				Condition cond, String condVariableMaybe, int condValue) {
			return new TransitionHolder(target,
	                effect,effectVar,effectArg,
	                cond, condVariableMaybe,condValue);
		}		
	}
	
}
