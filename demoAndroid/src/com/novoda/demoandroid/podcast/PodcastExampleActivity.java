package com.novoda.demoandroid.podcast;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.novoda.demo.advanced.podcast.parser.PodcastChannelParser;
import com.novoda.demo.advanced.podcast.pojo.Channel;
import com.novoda.demoandroid.SecondLevelBaseActivity;
import com.novoda.demoandroid.ParsingTask;
import com.novoda.demoandroid.R;
import com.novoda.sexp.Instigator;
import com.novoda.sexp.RootTag;
import com.novoda.sexp.SimpleEasyXmlParser;
import com.novoda.sexp.SimpleTagInstigator;
import com.novoda.sexp.finder.ElementFinder;
import com.novoda.sexp.finder.ElementFinderFactory;
import com.novoda.sexp.parser.ParseFinishWatcher;

public class PodcastExampleActivity extends SecondLevelBaseActivity {
	private static ElementFinder<Channel> elementFinder;
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
		elementFinder = factory.getTypeFinder(new PodcastChannelParser(factory));
		Instigator instigator = new PodcastInstigator(elementFinder, finishWatcher);

		new ParsingTask(PodcastExampleHelper.SINGLE_PODCAST_ITEM, instigator).execute();
	}

	private ParseFinishWatcher finishWatcher = new ParseFinishWatcher() {
		@Override
		public void onFinish() {
			Channel channel = elementFinder.getResultOrThrow();

			StringBuilder sb = new StringBuilder();
			String channelDetails = PodcastExampleHelper.getChannelDetailsString(channel);
			String channelImage = PodcastExampleHelper.getChannelImageString(channel.image);
			String podcasts = PodcastExampleHelper.getAllPodcastItemsString(channel.podcastItems);
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
