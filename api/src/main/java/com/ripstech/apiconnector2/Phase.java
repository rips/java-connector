package com.ripstech.apiconnector2;

import java.util.Arrays;

public enum Phase {
    DONE(0, "Done"),
    LOADING_FILES(1, "Loading files"),
    PARSING(2, "Parsing Code"),
    SCANNING(3, "Scanning code"),
    UNCALLED_FUNC(4, "Uncalled functions"),
    SECOND_ORDER(5, "Second-order 'Second-order vulnerabilities'"),
    AUTO_ABORT(6, "Scan automatically aborted"),
    USER_ABORT(7, "Scan aborted by user"),
    QUEUE(10, "Scan in queue. This may take a few minutes"),
    FINISHING(11, "Finishing the scan"),
    UNKNOWN(99, "Unknown");

    private final int phase;
    private final String description;

    Phase(int phase, String description) {
        this.phase = phase;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static Phase getById(int id) {
        return Arrays.stream(Phase.values())
                .filter(phase1 -> phase1.phase == id)
                .findFirst().orElse(UNKNOWN);
    }
}
