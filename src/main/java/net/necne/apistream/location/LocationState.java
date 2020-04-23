package net.necne.apistream.location;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LocationState {
    AVAILABLE("Available", true, false),
    LOCKED("Locked", false, false),
    AUDIT_NEEDED("Availble (audit)", true, true),
    AUDIT_LOCKED("Locked (audit)", false, true),
    ;

    private String label;
    private boolean changeQuantity;
    private boolean pendingReconciliation;
    
}