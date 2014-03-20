package com.novoda.demoandroid.team;

import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.novoda.demo.team.TeamMember;
import com.novoda.demoandroid.BaseActivity;
import com.novoda.demoandroid.ParsingTask;
import com.novoda.demoandroid.R;
import com.novoda.sexp.Instigator;
import com.novoda.sexp.RootTag;
import com.novoda.sexp.SimpleEasyXmlParser;
import com.novoda.sexp.SimpleTagInstigator;
import com.novoda.sexp.finder.ElementFinder;
import com.novoda.sexp.finder.ElementFinderFactory;
import com.novoda.sexp.parser.ParseFinishWatcher;

public class TeamExampleActivity extends BaseActivity {
	// language=XML
	private static final String XML = "<novoda>" + "<team>"
			+ "<name>Adam</name>" + "<name>Ben</name>" + "<name>Carl</name>"
			+ "<name>David</name>" + "<name>Franky</name>"
			+ "<name>Kevin</name>" + "<name>Moe</name>" + "<name>Paul</name>"
			+ "<name>Peter</name>" + "<name>Shiv</name>" + "</team>"
			+ "</novoda>";
	private static ElementFinder<List<TeamMember>> elementFinder;

	private TextView parsingResult;
	private ProgressBar progressBar;
	private LinearLayout container;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result_parsing);

		parsingResult = (TextView) findViewById(R.id.tv_result);
		progressBar = (ProgressBar) findViewById(R.id.pb_oneTag);
		container = (LinearLayout) findViewById(R.id.ll_oneTag);

		ElementFinderFactory factory = SimpleEasyXmlParser.getElementFinderFactory();
		elementFinder = factory.getStringWrapperTypeListFinder("name", TeamMember.class);
		Instigator instigator = new TeamInstigator(elementFinder, finishWatcher);

		new ParsingTask(XML, instigator).execute();
	}

	private ParseFinishWatcher finishWatcher = new ParseFinishWatcher() {
		@Override
		public void onFinish() {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					parsingResult.setText(elementFinder.getResultOrThrow()
							.toString());
					container.setVisibility(View.VISIBLE);
					progressBar.setVisibility(View.GONE);
				}
			});
		}
	};

	public static class TeamInstigator extends SimpleTagInstigator {

		public TeamInstigator(ElementFinder<?> elementFinder,
				ParseFinishWatcher parseFinishWatcher) {
			super(elementFinder, "team", parseFinishWatcher);
		}

		@Override
		public RootTag getRootTag() {
			return RootTag.create("novoda");
		}
	}

}
