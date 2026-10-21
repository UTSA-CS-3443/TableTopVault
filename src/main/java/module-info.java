module edu.utsa.cs3443.tabletopvault.group6 {
    requires javafx.controls;
    requires javafx.fxml;

    opens edu.utsa.cs3443.tabletopvault.group6 to javafx.fxml;
    opens edu.utsa.cs3443.tabletopvault.group6.controller to javafx.fxml;
    opens edu.utsa.cs3443.tabletopvault.group6.model to javafx.fxml;

    exports edu.utsa.cs3443.tabletopvault.group6;
    exports edu.utsa.cs3443.tabletopvault.group6.controller;
    exports edu.utsa.cs3443.tabletopvault.group6.model;
}