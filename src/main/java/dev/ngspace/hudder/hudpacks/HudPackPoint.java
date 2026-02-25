package dev.ngspace.hudder.hudpacks;

import static dev.ngspace.hudder.hudpacks.HudPackHudState.BOTTOMLEFT;
import static dev.ngspace.hudder.hudpacks.HudPackHudState.BOTTOMRIGHT;
import static dev.ngspace.hudder.hudpacks.HudPackHudState.TOPLEFT;
import static dev.ngspace.hudder.hudpacks.HudPackHudState.TOPRIGHT;

import java.io.IOException;

import dev.ngspace.hudder.compilers.utils.CompileException;
import dev.ngspace.hudder.compilers.utils.javascript.JavaScriptEngine;

public class HudPackPoint {
	
	
	public JavaScriptEngine engine;
	public String path;
	public HudPackPointConfig config;

	public HudPackPoint(HudPackPointConfig config, JavaScriptEngine engine) {
		this.config = config;
		this.engine = engine;
	}
	
	public void execute(HudPackHudState state) throws IOException, CompileException {
		switch (config.type()) {
			case TOPLEFT, BOTTOMLEFT, TOPRIGHT, BOTTOMRIGHT: 
				state.addString(engine.callFunction(config.entry_function()).asString(), config.type());
				break;
			case "mute", "elements": 
				engine.callFunction(config.entry_function());
				break;
			default:
				throw new IllegalArgumentException("Illegal point type: \"" + config.type() + '"');
		}
	}
}
