package com.jarasy.lv.common.utils;

import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by wjh on 2018/11/27 0027.
 */
public class ImageUtil {
    /**
     * 将网络图片编码为base64
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static String encodeImageToBase64(String url) throws Exception {
        //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        System.out.println("图片的路径为:" + url.toString());
        URL urlObj = new URL(url);
        //打开链接
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) urlObj.openConnection();
            //设置请求方式为"GET"
            conn.setRequestMethod("GET");
            //超时响应时间为5秒
            conn.setConnectTimeout(5 * 1000);
            //通过输入流获取图片数据
            InputStream inStream = conn.getInputStream();
            //得到图片的二进制数据，以二进制封装得到数据，具有通用性
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            //创建一个Buffer字符串
            byte[] buffer = new byte[1024];
            //每次读取的字符串长度，如果为-1，代表全部读取完毕
            int len = 0;
            //使用一个输入流从buffer里把数据读取出来
            while ((len = inStream.read(buffer)) != -1) {
                //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
                outStream.write(buffer, 0, len);
            }
            //关闭输入流
            inStream.close();
            byte[] data = outStream.toByteArray();
            //对字节数组Base64编码
            BASE64Encoder encoder = new BASE64Encoder();
            String base64 = encoder.encode(data);
            System.out.println("网络文件[{}]编码成base64字符串:[{}]"+url.toString()+base64);
            return base64;//返回Base64编码过的字节数组字符串
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("图片上传失败,请联系客服!");
        }
    }

    /**
     * 保存网络图片返回路径
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static String saveImageByURL(String url,String filename,String path) throws Exception {
        URL urlObj = new URL(url);
        //打开链接
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) urlObj.openConnection();
            //设置请求方式为"GET"
            conn.setRequestMethod("GET");
            //超时响应时间为5秒
            conn.setConnectTimeout(5 * 1000);
            //通过输入流获取图片数据
            InputStream inStream = conn.getInputStream();
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流
            File sf=new File(path);
            if(!sf.exists()){
                sf.mkdirs();
            }
            OutputStream os = new FileOutputStream(sf.getPath()+System.getProperty("file.separator")+filename);
            // 开始读取
            while ((len = inStream.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            // 完毕，关闭所有链接
            os.close();
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("图片上传失败,请联系客服!");
        }
        return null;
    }

    /*public static void main(String[] args) throws Exception {
        saveImageByURL("https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKHib59Lx6NBgtL6BHvrWXM8Nv5w4cicEfBrqs1rZ2OFjN1Odibuq5z4iatAld0ZDLl6y71hTl9IgbARg/132","asdfgh"+System.currentTimeMillis()+".png","f:/asdfg");
    }*/
}
