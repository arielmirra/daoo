package com.daoo.repl;

import daoo.repl.Environment;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.io.OutputStream;

/** Repl interface. */
public abstract class Repl {

    protected final Environment environment;

    protected Repl(@NotNull Environment environment) { this.environment = environment; }

    /** Loop on stream. */
    public abstract void loop(@NotNull InputStream input, @NotNull OutputStream output);
}
