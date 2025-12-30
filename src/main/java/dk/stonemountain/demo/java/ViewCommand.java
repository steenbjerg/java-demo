package dk.stonemountain.demo.java;

import java.util.Optional;

public record ViewCommand(Optional<String> brand, Optional<String> name) implements Command {
}
