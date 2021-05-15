package com.daoo.repl.implementations;

import java.util.HashMap;
import java.util.Map;

public class ReplRegistry {
    /** key = var or function, value = string to evaluate */
    public Map<String, String> declarations = new HashMap<>();
}
