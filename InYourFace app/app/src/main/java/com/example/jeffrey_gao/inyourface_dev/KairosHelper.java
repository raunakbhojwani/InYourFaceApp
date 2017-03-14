package com.example.jeffrey_gao.inyourface_dev;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Spinner;

import com.kairos.KairosListener;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.Base64;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by jeffreygao on 3/2/17.
 *
 * Helper class to enable more specific calls to Kairos to retrieve emotion and attention information
 */

public class KairosHelper {

    /**
     * Uploads image to Kairos for analysis
     */
    public static void postMedia(Context context, String image, final KairosListener callback)
            throws JSONException, UnsupportedEncodingException {
        AsyncHttpClient client = new AsyncHttpClient();
        AsyncHttpResponseHandler responseHandler = new AsyncHttpResponseHandler() {
            public void onStart() {
            }

            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                String responseString = new String(response);
                callback.onSuccess(responseString);
            }

            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                if (errorResponse != null) {
                    String responseString = new String(errorResponse);
                    callback.onFail(responseString);
                } else {
                    callback.onFail("post media failed");
                }
            }

            public void onRetry(int retryNo) {
            }
        };

        Log.d("jeff", context.getFilesDir() + "/" + image);
        File source = new File(context.getFilesDir() + "/" + image);
        RequestParams requestParams = new RequestParams();
        try {
            requestParams.put("source", source);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String mKey = context.getString(R.string.shared_preference);
        SharedPreferences mPrefs = context.getSharedPreferences(mKey, context.MODE_PRIVATE);
        String userName = mPrefs.getString(context.getResources().getString(R.string.user_name), "XD");

        String app_id = context.getResources().getString(R.string.kairos_app_id);
        String app_key = context.getResources().getString(R.string.kairos_app_key);

        switch (userName) {
            case "XD":
                break;
            case "Reshmi Suresh":
                app_id = context.getResources().getString(R.string.kairos_app_id2);
                app_key = context.getResources().getString(R.string.kairos_app_key2);
                break;
            case "Varun Mishra":
                app_id = context.getResources().getString(R.string.kairos_app_id3);
                app_key = context.getResources().getString(R.string.kairos_app_key3);
                break;
            case "Emma Oberstein":
                app_id = context.getResources().getString(R.string.kairos_app_id4);
                app_key = context.getResources().getString(R.string.kairos_app_key4);
                break;
            case "Virginia Cook":
                app_id = context.getResources().getString(R.string.kairos_app_id5);
                app_key = context.getResources().getString(R.string.kairos_app_key5);
                break;
            default:
                break;
        }

        client.addHeader("app_id", app_id);
        client.addHeader("app_key", app_key);
        Log.d("jeff", "posting");
        client.post(context, "http://api.kairos.com/v2/media", requestParams, responseHandler);
    }

    /**
     * Gets detailed information on a previously uploaded image
     */
    public static void getMedia(Context context, String id, final KairosListener callback)
            throws JSONException, UnsupportedEncodingException {
        AsyncHttpClient client = new AsyncHttpClient();
        AsyncHttpResponseHandler responseHandler = new AsyncHttpResponseHandler() {
            public void onStart() {
            }

            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                String responseString = new String(response);
                callback.onSuccess(responseString);
            }

            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                if (errorResponse != null) {
                    String responseString = new String(errorResponse);
                    callback.onFail(responseString);
                } else {
                    callback.onFail("get media failed");
                }
            }

            public void onRetry(int retryNo) {
            }
        };

        String mKey = context.getString(R.string.shared_preference);
        SharedPreferences mPrefs = context.getSharedPreferences(mKey, context.MODE_PRIVATE);
        String userName = mPrefs.getString(context.getResources().getString(R.string.user_name), "XD");

        String app_id = context.getResources().getString(R.string.kairos_app_id);
        String app_key = context.getResources().getString(R.string.kairos_app_key);

        switch (userName) {
            case "XD":
                break;
            case "Reshmi Suresh":
                app_id = context.getResources().getString(R.string.kairos_app_id2);
                app_key = context.getResources().getString(R.string.kairos_app_key2);
                break;
            case "Varun Mishra":
                app_id = context.getResources().getString(R.string.kairos_app_id3);
                app_key = context.getResources().getString(R.string.kairos_app_key3);
                break;
            case "Emma Oberstein":
                app_id = context.getResources().getString(R.string.kairos_app_id4);
                app_key = context.getResources().getString(R.string.kairos_app_key4);
                break;
            case "Virginia Cook":
                app_id = context.getResources().getString(R.string.kairos_app_id5);
                app_key = context.getResources().getString(R.string.kairos_app_key5);
                break;
            default:
                break;
        }

        client.addHeader("app_id", app_id);
        client.addHeader("app_key", app_key);
        client.get(context, "http://api.kairos.com/v2/media/" + id, responseHandler);
    }

    /*
     * Checks if user matches registered user
     */
    public static void recognize(Context context, Bitmap image, String galleryId, String selector, String threshold, String minHeadScale, String maxNumResults, final KairosListener callback)
            throws JSONException, UnsupportedEncodingException {
        AsyncHttpClient client = new AsyncHttpClient();
        AsyncHttpResponseHandler responseHandler = new AsyncHttpResponseHandler() {
            public void onStart() {
            }

            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                String responseString = new String(response);
                callback.onSuccess(responseString);
            }

            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                if (errorResponse != null) {
                    String responseString = new String(errorResponse);
                    callback.onFail(responseString);
                } else {
                    callback.onFail("Recognize failed");
                }
            }

            public void onRetry(int retryNo) {
            }
        };
        JSONObject jsonParams = new JSONObject();
        jsonParams.put("image", base64FromBitmap(image));
        jsonParams.put("gallery_name", galleryId);
        if(selector != null) {
            jsonParams.put("selector", selector);
        }

        if(minHeadScale != null) {
            jsonParams.put("minHeadScale", minHeadScale);
        }

        if(threshold != null) {
            jsonParams.put("threshold", threshold);
        }

        if(maxNumResults != null) {
            jsonParams.put("max_num_results", maxNumResults);
        }

        StringEntity entity = new StringEntity(jsonParams.toString());
        String mKey = context.getString(R.string.shared_preference);
        SharedPreferences mPrefs = context.getSharedPreferences(mKey, context.MODE_PRIVATE);
        String userName = mPrefs.getString(context.getResources().getString(R.string.user_name), "XD");

        String app_id = context.getResources().getString(R.string.kairos_app_id);
        String app_key = context.getResources().getString(R.string.kairos_app_key);

        switch (userName) {
            case "XD":
                break;
            case "Reshmi Suresh":
                app_id = context.getResources().getString(R.string.kairos_app_id2);
                app_key = context.getResources().getString(R.string.kairos_app_key2);
                break;
            case "Varun Mishra":
                app_id = context.getResources().getString(R.string.kairos_app_id3);
                app_key = context.getResources().getString(R.string.kairos_app_key3);
                break;
            case "Emma Oberstein":
                app_id = context.getResources().getString(R.string.kairos_app_id4);
                app_key = context.getResources().getString(R.string.kairos_app_key4);
                break;
            case "Virginia Cook":
                app_id = context.getResources().getString(R.string.kairos_app_id5);
                app_key = context.getResources().getString(R.string.kairos_app_key5);
                break;
            default:
                break;
        }

        client.addHeader("app_id", app_id);
        client.addHeader("app_key", app_key);
        client.post(context, "http://api.kairos.com/recognize", entity, "application/json", responseHandler);
    }

    /**
     * Deletes uploaded image once analysis is complete
     */
    public static void deleteMedia(Context context, String id, final KairosListener callback)
            throws JSONException, UnsupportedEncodingException{
        AsyncHttpClient client = new AsyncHttpClient();
        AsyncHttpResponseHandler responseHandler = new AsyncHttpResponseHandler() {
            public void onStart() {
            }

            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                String responseString = new String(response);
                callback.onSuccess(responseString);
            }

            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                if (errorResponse != null) {
                    String responseString = new String(errorResponse);
                    callback.onFail(responseString);
                } else {
                    callback.onFail("delete failed");
                }
            }

            public void onRetry(int retryNo) {
            }
        };

        String mKey = context.getString(R.string.shared_preference);
        SharedPreferences mPrefs = context.getSharedPreferences(mKey, context.MODE_PRIVATE);
        String userName = mPrefs.getString(context.getResources().getString(R.string.user_name), "XD");

        String app_id = context.getResources().getString(R.string.kairos_app_id);
        String app_key = context.getResources().getString(R.string.kairos_app_key);

        switch (userName) {
            case "XD":
                break;
            case "Reshmi Suresh":
                app_id = context.getResources().getString(R.string.kairos_app_id2);
                app_key = context.getResources().getString(R.string.kairos_app_key2);
                break;
            case "Varun Mishra":
                app_id = context.getResources().getString(R.string.kairos_app_id3);
                app_key = context.getResources().getString(R.string.kairos_app_key3);
                break;
            case "Emma Oberstein":
                app_id = context.getResources().getString(R.string.kairos_app_id4);
                app_key = context.getResources().getString(R.string.kairos_app_key4);
                break;
            case "Virginia Cook":
                app_id = context.getResources().getString(R.string.kairos_app_id5);
                app_key = context.getResources().getString(R.string.kairos_app_key5);
                break;
            default:
                break;
        }

        client.addHeader("app_id", app_id);
        client.addHeader("app_key", app_key);
        client.delete(context, "http://api.kairos.com/v2/media/" + id, responseHandler);
    }

    public static void enroll(Context context, Bitmap image, String subjectId, String galleryId, String selector, String multipleFaces, String minHeadScale, final KairosListener callback) throws JSONException, UnsupportedEncodingException {
        AsyncHttpClient client = new AsyncHttpClient();
        AsyncHttpResponseHandler responseHandler = new AsyncHttpResponseHandler() {
            public void onStart() {
            }

            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                String responseString = new String(response);
                callback.onSuccess(responseString);
            }

            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                if (errorResponse != null) {
                    String responseString = new String(errorResponse);
                    callback.onFail(responseString);
                } else {
                    callback.onFail("enroll failed");
                }
            }

            public void onRetry(int retryNo) {
            }
        };
        JSONObject jsonParams = new JSONObject();
        jsonParams.put("image", base64FromBitmap(image));
        jsonParams.put("subject_id", subjectId);
        jsonParams.put("gallery_name", galleryId);
        if(selector != null) {
            jsonParams.put("selector", selector);
        }

        if(minHeadScale != null) {
            jsonParams.put("minHeadScale", minHeadScale);
        }

        if(multipleFaces != null) {
            jsonParams.put("multiple_faces", multipleFaces);
        }

        StringEntity entity = new StringEntity(jsonParams.toString());
        String mKey = context.getString(R.string.shared_preference);
        SharedPreferences mPrefs = context.getSharedPreferences(mKey, context.MODE_PRIVATE);
        String userName = mPrefs.getString(context.getResources().getString(R.string.user_name), "XD");

        String app_id = context.getResources().getString(R.string.kairos_app_id);
        String app_key = context.getResources().getString(R.string.kairos_app_key);

        switch (userName) {
            case "XD":
                break;
            case "Reshmi Suresh":
                app_id = context.getResources().getString(R.string.kairos_app_id2);
                app_key = context.getResources().getString(R.string.kairos_app_key2);
                break;
            case "Varun Mishra":
                app_id = context.getResources().getString(R.string.kairos_app_id3);
                app_key = context.getResources().getString(R.string.kairos_app_key3);
                break;
            case "Emma Oberstein":
                app_id = context.getResources().getString(R.string.kairos_app_id4);
                app_key = context.getResources().getString(R.string.kairos_app_key4);
                break;
            case "Virginia Cook":
                app_id = context.getResources().getString(R.string.kairos_app_id5);
                app_key = context.getResources().getString(R.string.kairos_app_key5);
                break;
            default:
                break;
        }

        client.addHeader("app_id", app_id);
        client.addHeader("app_key", app_key);
        client.post(context, "http://api.kairos.com/enroll", entity, "application/json", responseHandler);
    }

    public static void listGalleries(Context context, final KairosListener callback) throws JSONException, UnsupportedEncodingException {
        AsyncHttpClient client = new AsyncHttpClient();
        AsyncHttpResponseHandler responseHandler = new AsyncHttpResponseHandler() {
            public void onStart() {
            }

            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                String responseString = new String(response);
                callback.onSuccess(responseString);
            }

            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                if (errorResponse != null) {
                    String responseString = new String(errorResponse);
                    callback.onFail(responseString);
                } else {
                    callback.onFail("list galleries failed");
                }
            }

            public void onRetry(int retryNo) {
            }
        };
        JSONObject jsonParams = new JSONObject();
        StringEntity entity = new StringEntity(jsonParams.toString());
        String mKey = context.getString(R.string.shared_preference);
        SharedPreferences mPrefs = context.getSharedPreferences(mKey, context.MODE_PRIVATE);
        String userName = mPrefs.getString(context.getResources().getString(R.string.user_name), "XD");

        String app_id = context.getResources().getString(R.string.kairos_app_id);
        String app_key = context.getResources().getString(R.string.kairos_app_key);

        switch (userName) {
            case "XD":
                break;
            case "Reshmi Suresh":
                app_id = context.getResources().getString(R.string.kairos_app_id2);
                app_key = context.getResources().getString(R.string.kairos_app_key2);
                break;
            case "Varun Mishra":
                app_id = context.getResources().getString(R.string.kairos_app_id3);
                app_key = context.getResources().getString(R.string.kairos_app_key3);
                break;
            case "Emma Oberstein":
                app_id = context.getResources().getString(R.string.kairos_app_id4);
                app_key = context.getResources().getString(R.string.kairos_app_key4);
                break;
            case "Virginia Cook":
                app_id = context.getResources().getString(R.string.kairos_app_id5);
                app_key = context.getResources().getString(R.string.kairos_app_key5);
                break;
            default:
                break;
        }

        Log.d("KAIROS HELPER", "using " + app_id + " and " + app_key);

        client.addHeader("app_id", app_id);
        client.addHeader("app_key", app_key);
        client.post(context, "http://api.kairos.com/gallery/list_all", entity, "application/json", responseHandler);
    }

    /*
     * Retrieves summary data on an uploaded image
     */
    public static void analytics(Context context, String id, final KairosListener callback)
            throws JSONException, UnsupportedEncodingException {
        AsyncHttpClient client = new AsyncHttpClient();
        AsyncHttpResponseHandler responseHandler = new AsyncHttpResponseHandler() {
            public void onStart() {
            }

            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                String responseString = new String(response);
                callback.onSuccess(responseString);
            }

            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                if (errorResponse != null) {
                    String responseString = new String(errorResponse);
                    callback.onFail(responseString);
                } else {
                    callback.onFail("analytics failed");
                }
            }

            public void onRetry(int retryNo) {
            }
        };

        String mKey = context.getString(R.string.shared_preference);
        SharedPreferences mPrefs = context.getSharedPreferences(mKey, context.MODE_PRIVATE);
        String userName = mPrefs.getString(context.getResources().getString(R.string.user_name), "XD");

        String app_id = context.getResources().getString(R.string.kairos_app_id);
        String app_key = context.getResources().getString(R.string.kairos_app_key);

        switch (userName) {
            case "XD":
                break;
            case "Reshmi Suresh":
                app_id = context.getResources().getString(R.string.kairos_app_id2);
                app_key = context.getResources().getString(R.string.kairos_app_key2);
                break;
            case "Varun Mishra":
                app_id = context.getResources().getString(R.string.kairos_app_id3);
                app_key = context.getResources().getString(R.string.kairos_app_key3);
                break;
            case "Emma Oberstein":
                app_id = context.getResources().getString(R.string.kairos_app_id4);
                app_key = context.getResources().getString(R.string.kairos_app_key4);
                break;
            case "Virginia Cook":
                app_id = context.getResources().getString(R.string.kairos_app_id5);
                app_key = context.getResources().getString(R.string.kairos_app_key5);
                break;
            default:
                break;
        }

        client.addHeader("app_id", app_id);
        client.addHeader("app_key", app_key);
        client.get(context, "http://api.kairos.com/v2/analytics/" + id, responseHandler);
    }

    /*
     * Deletes a specific gallery
     */
    public static void deleteGallery(Context context, String galleryId, final KairosListener callback) throws JSONException, UnsupportedEncodingException {
        AsyncHttpClient client = new AsyncHttpClient();
        AsyncHttpResponseHandler responseHandler = new AsyncHttpResponseHandler() {
            public void onStart() {
            }

            public void onSuccess(int statusCode, Header[] headers, byte[] response) {
                String responseString = new String(response);
                callback.onSuccess(responseString);
            }

            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                if (errorResponse != null) {
                    String responseString = new String(errorResponse);
                    callback.onFail(responseString);
                } else {
                    callback.onFail("delete gallery failed");
                }
            }

            public void onRetry(int retryNo) {
            }
        };
        JSONObject jsonParams = new JSONObject();
        jsonParams.put("gallery_name", galleryId);
        StringEntity entity = new StringEntity(jsonParams.toString());
        String mKey = context.getString(R.string.shared_preference);
        SharedPreferences mPrefs = context.getSharedPreferences(mKey, context.MODE_PRIVATE);
        String userName = mPrefs.getString(context.getResources().getString(R.string.user_name), "XD");

        String app_id = context.getResources().getString(R.string.kairos_app_id);
        String app_key = context.getResources().getString(R.string.kairos_app_key);

        switch (userName) {
            case "XD":
                break;
            case "Reshmi Suresh":
                app_id = context.getResources().getString(R.string.kairos_app_id2);
                app_key = context.getResources().getString(R.string.kairos_app_key2);
                break;
            case "Varun Mishra":
                app_id = context.getResources().getString(R.string.kairos_app_id3);
                app_key = context.getResources().getString(R.string.kairos_app_key3);
                break;
            case "Emma Oberstein":
                app_id = context.getResources().getString(R.string.kairos_app_id4);
                app_key = context.getResources().getString(R.string.kairos_app_key4);
                break;
            case "Virginia Cook":
                app_id = context.getResources().getString(R.string.kairos_app_id5);
                app_key = context.getResources().getString(R.string.kairos_app_key5);
                break;
            default:
                break;
        }

        client.addHeader("app_id", app_id);
        client.addHeader("app_key", app_key);
        client.post(context, "http://api.kairos.com/gallery/remove", entity, "application/json", responseHandler);
    }

    protected static String base64FromBitmap(Bitmap image) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        String encoded = Base64.encodeToString(byteArray, 0);
        return encoded;
    }
}
