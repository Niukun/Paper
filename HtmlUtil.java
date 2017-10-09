package niukun.utils;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HtmlUtil {

	public static void main(String[] args) {
	}

	/**
	 * 根据url从网页获取文本
	 * @param url
	 * @return
	 */
	public Document getHtmlTextByUrl(String url) {
		Document doc = null;
		try {
			int i = (int)(Math.random()*1000);//随机延迟，防止网站屏蔽
			while(i!=0) {
				i--;
			}
			doc = Jsoup.connect(url).data("query","java").userAgent("Mosilla").cookie("auth", "token").timeout(3000000).post();
 		}catch (IOException e) {
 			e.printStackTrace();
 			try {
				doc = Jsoup.connect(url).timeout(5000000).get();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return doc;
	}
}
