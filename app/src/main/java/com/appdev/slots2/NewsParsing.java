package com.appdev.slots2;

import android.util.Log;
import android.os.*;

import com.appdev.slots2.models.NewsModel;
import com.appdev.slots2.utils.DateFormat;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

class NewsParsing extends AsyncTask<Void, Void, Void>
{ 

	interface OnWorkDoneListener
	{ 
		void done(ArrayList<NewsModel> list);
	}

	OnWorkDoneListener listener;
	public NewsParsing(OnWorkDoneListener listener)
	{ 
		this.listener = listener;

	}

	private final String url= "https://lenta.ru/rss/news";
	ArrayList<NewsModel> stringList = new ArrayList<>();

	@Override
	protected void onPreExecute()
	{
		super.onPreExecute();
	}

	@Override
	protected Void doInBackground(Void... voids)
	{
		try
		{
			Document doc = Jsoup.parse(new URL(url).openStream(), "UTF-8", url, Parser.xmlParser());

            String source = doc.select("title").first().text();


            Elements elements = doc.select("item");

            for (int i = 0; i < elements.size(); i++)
			{

                Element element = elements.get(i);

                String enclosure = element.select("enclosure").attr("url");

                stringList.add(new NewsModel(
								   i,
								   element.select("title").text(),
								   source,
								   element.select("link").text(),
								   element.select("guid").text(),
								   enclosure,
								   element.select("description").text(),
								   DateFormat.getFormattedDate(element.select("pubDate").text(), false)
							   ));

                Log.e("getNews", stringList.get(i).source + " !!!");
            }

		}
		catch (IOException e)
		{
			Log.e("doInBackground", e.getMessage());
		}
		return null;
	}

	@Override
	protected void onPostExecute(Void aVoid)
	{

		listener.done(stringList);
	}
}



    //public static Observable<List<NewsModel>> getString() {
       // NewsParsing webParsing = new NewsParsing();
        //return Observable.create(observableEmitter -> {
            //observableEmitter.onNext(webParsing.getNews("https://lenta.ru/rss/news"));
           // observableEmitter.onNext(webParsing.getNews("https://meduza.io/rss/all"));
           // observableEmitter.onNext(webParsing.getNews("https://zona.media/rss"));
           // observableEmitter.onNext(webParsing.getNews("https://www.znak.com/rss"));
           // observableEmitter.onNext(webParsing.getNews("https://tvrain.ru/export/rss/all.xml"));
           // observableEmitter.onNext(webParsing.getNews("https://rss.dw.com/xml/rss-ru-news"));
           // observableEmitter.onComplete();
       // });
    //}

    


