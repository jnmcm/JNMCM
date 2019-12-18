package application.view;

import javafx.fxml.FXML;
import javafx.scene.web.WebView;

public class AboutController {

	@FXML
	private WebView webView;


	@FXML
	void initialize() {
		String url = getClass().getResource("about.html").toExternalForm();
	    webView.getEngine().load(url);
	}
}
