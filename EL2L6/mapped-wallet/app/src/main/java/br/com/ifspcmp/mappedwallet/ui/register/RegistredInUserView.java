package br.com.ifspcmp.mappedwallet.ui.register;

/**
 * Class exposing authenticated user details to the UI.
 */
class RegistredInUserView {
    private String displayName;
    //... other data fields that may be accessible to the UI

    RegistredInUserView(String displayName) {
        this.displayName = displayName;
    }

    String getDisplayName() {
        return displayName;
    }
}
