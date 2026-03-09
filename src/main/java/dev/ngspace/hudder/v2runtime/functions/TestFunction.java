package dev.ngspace.hudder.v2runtime.functions;

import dev.ngspace.hudder.exceptions.ExecutionException;
import dev.ngspace.hudder.v2runtime.V2Runtime;
import dev.ngspace.hudder.v2runtime.values.AV2Value;

public class TestFunction implements IV2Function {

	@Override public Object execute(V2Runtime runtime, String functionName, AV2Value[] args, int line, int charpos)
			throws ExecutionException {
		return args[0].asString() + args[1].asString();
	}
	
}
