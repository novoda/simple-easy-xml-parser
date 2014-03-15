package com.example.demoandroid.onetag;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.demoandroid.BaseActivity;
import com.example.demoandroid.ParsingTask;
import com.example.demoandroid.R;
import com.novoda.sexp.Instigator;
import com.novoda.sexp.SimpleEasyXmlParser;
import com.novoda.sexp.finder.ElementFinder;
import com.novoda.sexp.finder.ElementFinderFactory;
import com.novoda.sexp.parser.ParseFinishWatcher;

public class OneTagActivity extends BaseActivity {
	public static final String TITLE = "One Tag Example";

	// language=XML
	private static final String XML = "<novoda>Hello XML</novoda>";
	private static ElementFinder<String> elementFinder;

	private TextView parsingResult;
	private ProgressBar progressBar;
	private LinearLayout container;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result_parsing);
		getActionBar().setTitle(TITLE);

		parsingResult = (TextView) findViewById(R.id.tv_result);
		progressBar = (ProgressBar) findViewById(R.id.pb_oneTag);
		container = (LinearLayout) findViewById(R.id.ll_oneTag);

		ElementFinderFactory factory = SimpleEasyXmlParser
				.getElementFinderFactory();
		elementFinder = factory.getStringFinder();
		Instigator instigator = new OneElementInstigator(elementFinder,
				"novoda", finishWatcher);

		new Thread(new ParsingTask(XML, instigator)).start();
	}

	private ParseFinishWatcher finishWatcher = new ParseFinishWatcher() {
		@Override
		public void onFinish() {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					parsingResult.setText(elementFinder.getResultOrThrow());
					container.setVisibility(View.VISIBLE);
					progressBar.setVisibility(View.GONE);
				}
			});

		}
	};

}
