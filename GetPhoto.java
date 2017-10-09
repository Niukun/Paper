package niukun.utils;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetPhoto {
	public static void main(String[] args) {
		System.out.println("*************************");
		try {
			go(1, 49);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("*************************");
	}
	public static void go(int startpage,int endpage) throws IOException {
		HtmlUtil htmlutil = new HtmlUtil();
		//获取图片的绝对路径
		String url = "http://bing.plmeizi.com/?page=";
		for (int i = startpage; i < endpage; i++) {
			String gourl = url + i + "";
			Document dochtml = htmlutil.getHtmlTextByUrl(gourl);
			Elements elements_a = dochtml.getElementsByClass("item");
			for (int x = 0; x < elements_a.size(); x++) {
				String pyotopage = elements_a.get(x).attr("href");
				Document dochtml_photo = htmlutil.getHtmlTextByUrl(pyotopage);
				Element  element_picurl = dochtml_photo.getElementById("picurl");
				String picurl = element_picurl.attr("href");
				Element element_searchlink = dochtml_photo.getElementById("searchlink");
				String name = element_searchlink.getElementsByTag("span").get(0).html();
				name = name.split("\\(")[0];
				if(picurl.contains("jpg")) {
					URL url_pic = new URL(picurl);
					DataInputStream dis = new DataInputStream(url_pic.openStream());
					String imagename = name + ".jpg";
					FileOutputStream fis = new FileOutputStream(new File("d:/image/"+imagename));
					byte[] buffer = new byte[1024];
					int length = 0;
					while((length = dis.read(buffer))!=-1){
						fis.write(buffer, 0, length);
					}
					fis.close();
					dis.close();
				}
			}
		}
	}
}
