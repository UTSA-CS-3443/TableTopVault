module edu.utsa.cs3443.tabletopvault {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens edu.utsa.cs3443.tabletopvault.group6 to javafx.fxml;
    exports edu.utsa.cs3443.tabletopvault.group6;
    exports edu.utsa.cs3443.tabletopvault.group6.controller;
    opens edu.utsa.cs3443.tabletopvault.group6.controller to javafx.fxml;
}