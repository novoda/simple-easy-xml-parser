package com.example.demoandroid.podcast;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.demoandroid.BaseActivity;
import com.example.demoandroid.ParsingTask;
import com.example.demoandroid.R;
import com.example.demoandroid.advanced.podcast.parser.PodcastChannelParser;
import com.example.demoandroid.podcast.pojo.Channel;
import com.novoda.sexp.Instigator;
import com.novoda.sexp.RootTag;
import com.novoda.sexp.SimpleEasyXmlParser;
import com.novoda.sexp.SimpleTagInstigator;
import com.novoda.sexp.finder.ElementFinder;
import com.novoda.sexp.finder.ElementFinderFactory;
import com.novoda.sexp.parser.ParseFinishWatcher;

public class PodcastExampleActivity extends BaseActivity {
	public static final String TITLE = "Podcast Example";

	private static ElementFinder<Channel> elementFinder;

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
		elementFinder = factory
				.getTypeFinder(new PodcastChannelParser(factory));
		Instigator instigator = new PodcastInstigator(elementFinder,
				finishWatcher);

		new Thread(new ParsingTask(PodcastExampleHelper.SINGLE_PODCAST_ITEM,
				instigator)).start();
	}

	private ParseFinishWatcher finishWatcher = new ParseFinishWatcher() {
		@Override
		public void onFinish() {
			Channel channel = elementFinder.getResultOrThrow();

			StringBuilder sb = new StringBuilder();
			String channelDetails = PodcastExampleHelper
					.getChannelDetailsString(channel);
			String channelImage = PodcastExampleHelper
					.getChannelImageString(channel.image);
			String podcasts = PodcastExampleHelper
					.getAllPodcastItemsString(channel.podcastItems);
			String space = PodcastExampleHelper.getSpace();

			sb.append(channelDetails);
			sb.append(channelImage);
			sb.append(space);
			sb.append(podcasts);
			final String result = sb.toString();

			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					parsingResult.setText(result);
					container.setVisibility(View.VISIBLE);
					progressBar.setVisibility(View.GONE);
				}
			});
		}
	};

	public static class PodcastInstigator extends SimpleTagInstigator {

		public PodcastInstigator(ElementFinder<?> elementFinder,
				ParseFinishWatcher parseFinishWatcher) {
			super(elementFinder, "channel", parseFinishWatcher);
		}

		@Override
		public RootTag getRootTag() {
			return RootTag.create("rss");
		}
	}

}
