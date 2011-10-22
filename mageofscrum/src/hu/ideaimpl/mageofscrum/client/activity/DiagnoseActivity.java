package hu.ideaimpl.mageofscrum.client.activity;

import hu.ideaimpl.mageofscrum.client.ClientFactory;
import hu.ideaimpl.mageofscrum.client.view.DiagnoseView;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

public class DiagnoseActivity extends AbstractActivity {
	private DiagnoseView view = ClientFactory.Util.getClientFactory().getDiagnoseView();

	public DiagnoseActivity() {
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		panel.setWidget(view);
	}

}
