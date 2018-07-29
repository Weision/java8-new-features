package com.wxx.base64;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.UUID;

public class Base64Client {
    public static void main(String[] args) throws UnsupportedEncodingException {
        // Encode using basic encoder
        String base64encodedString = Base64.getEncoder().encodeToString("尘埃安然".getBytes("utf-8"));
        System.out.println("Base64编码后(Base):" + base64encodedString);
        // Decode using basic encoder
        byte[] base64decodedBytes = Base64.getDecoder().decode(base64encodedString);
        System.out.println("Base64解码后(Base): " + new String(base64decodedBytes, "utf-8"));

        // Encode using URL encoder
        String base64UrlEncodedString = Base64.getUrlEncoder().encodeToString("仓央嘉措".getBytes("utf-8"));
        System.out.println("Base64 编码后(URL) :" + base64UrlEncodedString);
        // Decode using URL encoder
        String base64UrlDecodedString = new String(Base64.getUrlDecoder().decode(base64UrlEncodedString));
        System.out.println("Base64 解码后(URL) :" + base64UrlDecodedString);

        // Encode using URL encoder
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 4; ++i) {
            stringBuilder.append(UUID.randomUUID().toString());
        }
        byte[] mimeBytes = stringBuilder.toString().getBytes("utf-8");
        String mimeEncodedString = Base64.getMimeEncoder().encodeToString(mimeBytes);
        System.out.println("Base64 编码后 (MIME) :" + mimeEncodedString);
        // Decode using URL encoder
        String mimeDncodedString = new String(Base64.getMimeDecoder().decode(mimeEncodedString));
        System.out.println("Base64 解码后 (MIME) :" + mimeDncodedString);
    }
}
