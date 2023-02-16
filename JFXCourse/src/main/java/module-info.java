module edu.nemom.birzeit.aiproject.jfxcourse {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.nemom.birzeit.aiproject.jfxcourse to javafx.fxml;
    exports edu.nemom.birzeit.aiproject.jfxcourse;
    exports edu.nemom.birzeit.aiproject.jfxcourse.Controller;
    opens edu.nemom.birzeit.aiproject.jfxcourse.Controller to javafx.fxml;
    exports edu.nemom.birzeit.aiproject.jfxcourse.Models;
    opens edu.nemom.birzeit.aiproject.jfxcourse.Models to javafx.fxml;
}