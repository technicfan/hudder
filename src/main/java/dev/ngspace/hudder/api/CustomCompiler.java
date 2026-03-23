package dev.ngspace.hudder.api;

import dev.ngspace.hudder.compilers.abstractions.AV2Compiler;
import dev.ngspace.hudder.compilers.utils.TextPos;
import dev.ngspace.hudder.config.HudderConfig;
import dev.ngspace.hudder.exceptions.CompileException;
import dev.ngspace.hudder.exceptions.ExecutionException;
import dev.ngspace.hudder.v2runtime.V2Runtime;
import dev.ngspace.hudder.v2runtime.runtime_elements.StringV2RuntimeElement;
import dev.ngspace.hudder.v2runtime.runtime_elements.VariableV2RuntimeElement;

public class CustomCompiler extends AV2Compiler {
	@Override
	public V2Runtime buildRuntime(HudderConfig info, String text, TextPos charPosition, String filename, V2Runtime scope) throws CompileException {
//Init the runtime
V2Runtime runtime = new V2Runtime(this, scope);
StringBuilder textBuilder = new StringBuilder();

//Loop through every character in the file
for (int ind = 0;ind<text.length();ind++) {
	char c = text.charAt(ind);
	if (c=='%') {
		StringBuilder variableBuilder = new StringBuilder();
		runtime.addRuntimeElement(new StringV2RuntimeElement(textBuilder.toString(), false, true));
		//Reset the text builder
		textBuilder.setLength(0);
		while (ind<text.length()) {
			ind++;
			c = text.charAt(ind);
			if (c=='%') {
				TextPos position = getPosition(ind, text);
				//Add whatever text is left in the text builder
				try {
					runtime.addRuntimeElement(new VariableV2RuntimeElement(variableBuilder.toString(), this, runtime, position.line(), position.column()));
				} catch (ExecutionException e) {
					e.printStackTrace();
					throw new CompileException(e);
				}
				break;
			}	
			variableBuilder.append(c);
		}
	} else textBuilder.append(c);
}
//Add whatever text is left in the text builder
runtime.addRuntimeElement(new StringV2RuntimeElement(textBuilder.toString(), false, true));
return runtime;
	}
}