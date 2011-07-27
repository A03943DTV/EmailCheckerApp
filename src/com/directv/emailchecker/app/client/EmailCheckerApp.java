/*
 * Author  : Meiyazhagan Arjunan
 * Company : Ilink Multitech Solutions
 */
package com.directv.emailchecker.app.client;

import com.directv.emailchecker.app.dialog.LoadingDialogBox;
import com.directv.emailchecker.app.dialog.NormalDialogBox;
import com.directv.emailchecker.app.shared.FieldVerifier;
import com.directv.emailchecker.app.shared.Response;
import com.directv.emailchecker.app.util.ECException;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

// TODO: Auto-generated Javadoc
/**
 * The Class EmailCheckerApp.
 */
public class EmailCheckerApp implements EntryPoint {

	/** The Constant CONTAINER. */
	private static final String CONTAINER = "container";

	/** The email id label. */
	private Label emailIdLabel = new Label("Enter the EmailId");

	/** The email id text field. */
	private TextBox emailIdTextField = new TextBox();

	/** The verify button. */
	private Button verifyButton = new Button("Verify");

	/** The clear button. */
	private Button clearButton = new Button("Clear");

	/** The email checker service. */
	private final EmailCheckerServiceAsync emailCheckerService = GWT.create(EmailCheckerService.class);

	/** The v panel main. */
	private VerticalPanel vPanelMain = new VerticalPanel();

	/** The v panel result. */
	private VerticalPanel vPanelResult = new VerticalPanel();

	/** The loading dialog box. */
	private LoadingDialogBox loadingDialogBox;

	/** The form grid. */
	private Grid formGrid = new Grid(1, 5);

	/**
	 * Overridden Method
	 */
	public void onModuleLoad() {

		Image headerImage = new Image();
		headerImage.setUrl("images/header.jpg");
		headerImage.setHeight("65px");
		headerImage.setWidth("700px");

		emailIdTextField.setWidth("210px");
		formGrid.setWidget(0, 0, emailIdLabel);
		formGrid.setWidget(0, 1, emailIdTextField);
		formGrid.setWidget(0, 2, verifyButton);
		formGrid.setWidget(0, 3, clearButton);
		formGrid.setCellSpacing(5);
		formGrid.setHeight("50px");

		verifyButtonHandler();
		clearButtonHandler();

		vPanelMain.add(headerImage);
		vPanelMain.add(formGrid);
		vPanelMain.add(vPanelResult);
		vPanelMain.setSpacing(5);

		RootPanel.get(CONTAINER).add(vPanelMain);
	}

	/**
	 * Clear button handler.
	 */
	private void clearButtonHandler() {

		clearButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				emailIdTextField.setText("");
				vPanelResult.clear();
				formGrid.setWidget(0, 4, null);
			}
		});
	}

	/**
	 * Verify button handler.
	 */
	private void verifyButtonHandler() {

		verifyButton.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {

				try {
					FieldVerifier.validate(emailIdTextField.getText());
				} catch (ECException e) {
					new NormalDialogBox("Validation Error", e.getMessage());
					return;
				}

				loadingDialogBox = new LoadingDialogBox("Verifying.....", "Verifying emailid..... Please wait for few seconds.....");

				emailCheckerService.validateEmailId(emailIdTextField.getText(), new AsyncCallback<Response>() {

					@Override
					public void onFailure(Throwable caught) {
						caught.printStackTrace();
						loadingDialogBox.hideLoaderDialog();
						new NormalDialogBox("Validation Error", "Error occured while validating EmailId : " + caught.getMessage());
					}

					@Override
					public void onSuccess(Response result) {

						if (result.getEmailchecker() != null) {

							Grid resultGrid = new Grid(4, 2);
							resultGrid.setWidget(0, 0, new Label("EmailId"));
							resultGrid.setWidget(0, 1, new Label(": " + String.valueOf(result.getEmailchecker().getEmailid())));
							resultGrid.setWidget(1, 0, new Label("IsValid"));
							resultGrid.setWidget(1, 1, new Label(": " + String.valueOf(result.getEmailchecker().isIsvalid())));
							resultGrid.setWidget(2, 0, new Label("Status"));
							resultGrid.setWidget(2, 1, new Label(": " + result.getStatus()));
							resultGrid.setWidget(3, 0, new Label("Content"));
							resultGrid.setWidget(3, 1, new Label(": " + result.getEmailchecker().getContent()));

							addResultGridToPanel(resultGrid);
							setImageInFormGrid(result.getEmailchecker().isIsvalid());

						} else if (result.getError() != null) {

							Grid resultGrid = new Grid(2, 2);
							resultGrid.setWidget(0, 0, new Label("Status"));
							resultGrid.setWidget(0, 1, new Label(": " + result.getStatus()));
							resultGrid.setWidget(1, 0, new Label("Error Message"));
							resultGrid.setWidget(1, 1, new Label(": " + result.getError().getErrormessage()));

							addResultGridToPanel(resultGrid);
							formGrid.setWidget(0, 4, null);
						}

						loadingDialogBox.hideLoaderDialog();
					}
				});
			}
		});
	}

	/**
	 * Sets the image in form grid.
	 *
	 * @param isValid the new image in form grid
	 */
	protected void setImageInFormGrid(boolean isValid) {

		Image isValidImage = new Image();
		if (isValid) {
			isValidImage.setUrl("images/correct.png");
		} else {
			isValidImage.setUrl("images/wrong.png");
		}
		formGrid.setWidget(0, 4, isValidImage);
	}

	/**
	 * Adds the result grid to panel.
	 *
	 * @param resultGrid the result grid
	 */
	protected void addResultGridToPanel(Grid resultGrid) {

		resultGrid.setCellSpacing(5);

		DecoratorPanel decPanel = new DecoratorPanel();
		decPanel.setWidth("700px");
		decPanel.add(resultGrid);

		vPanelResult.clear();
		vPanelResult.add(decPanel);
	}
}
