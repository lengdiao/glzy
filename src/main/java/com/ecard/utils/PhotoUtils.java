package com.ecard.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class PhotoUtils {
		// 上传头像保存根路径
		private static String rootpath = "../../../../webapps/glzy/WEB-INF/classes/static/images";
		// 保存目录
		private static String savepath = "";
		// 图片的base64编码
		private static StringBuffer data;
		// 判断文件保存类型
		private static String fileType = "";
		// 文件名称
		private static String filename = "";
	 
		// 构造函数
		public PhotoUtils(StringBuffer stringBuffer,String mallNo,String name) {
			int xiegang = stringBuffer.indexOf("/");
			int fenhao = stringBuffer.indexOf(";");
			int douhao = stringBuffer.indexOf(",");
			fileType = stringBuffer.substring(xiegang + 1, fenhao);
			data = stringBuffer.delete(0, douhao + 1);
			filename = mallNo+name + "." + fileType;
			savepath = rootpath + filename;
			System.out.println(savepath);
		}
				
		// 将base64转成图片
		private static String changeBase64() {
			try {
				byte[] bytes = Base64.decodeBase64(data.toString());
				for (int i = 0; i < bytes.length; ++i) {
					if (bytes[i] < 0) {// 调整异常数据
						bytes[i] += 256;
					}
				}
				// 生成图片
				OutputStream out = new FileOutputStream(savepath);
				out.write(bytes);
				out.flush();
				out.close();
				return savepath;
			} catch (Exception e) {
				System.out.println("生成图片失败：PhotoUtils.changeBase64()");
				return null;
			}
		}
	 
		public static String getSavepath() {
			return savepath;
		}
	 
		public static void setSavepath(String savepath) {
			PhotoUtils.savepath = savepath;
		}
	 
		// 执行操作
		public String open() {
			return changeBase64();
		}

}
