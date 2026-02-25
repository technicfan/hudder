package dev.ngspace.hudder.hudpacks;

public record HudPackPointConfig(String type, String path, String entry_function, String[] conditions) {
	
	public HudPackPointConfig(String type, String path, String entry_function) {
		this(type, path, entry_function, null);
	}
	
}