package com.example.mvvmandlivedata.webviewactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mvvmandlivedata.R;

public class WebViewActivity extends AppCompatActivity {
//    private WebView webView;
//    private ImageView imageView;
//    private boolean itemIsSave =false;
//    private ConnectionDB db = null;
//    private Dialog dialog;
//    private Intent intent;
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_activity);

//        dialog = new Dialog(this,android.R.style.Theme_DeviceDefault_Light_NoActionBar_Fullscreen);
//        db = new ConnectionDB(getApplicationContext());
//        intent = getIntent();
//
//        setWebView(savedInstanceState);
//        setImageView();
//        saveOrDeleteItem();

    }

//    public void setWebView(Bundle savedInstanceState){
//        webView = findViewById(R.id.webview);
//        webView.getSettings().setJavaScriptEnabled(true);
//
//        webView.setWebViewClient(new WebViewClient() {
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                openDialog(dialog);
//                super.onPageStarted(view, url, favicon);
//            }
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                closeDialog(dialog);
//                super.onPageFinished(view, url);
//            }
//
//        });
//
//        if (savedInstanceState != null){
//            webView.restoreState(savedInstanceState);
//        }
//        else{
//            webView.loadUrl(intent.getStringExtra("itemUrl"));
//            webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//        }
//    }
//
//    public void setImageView(){
//        imageView = findViewById(R.id.imageView);
//        if(intent.getBooleanExtra("iconSave",false)){
//            itemIsSave =true;
//            imageView.setImageResource(R.drawable.starsave);
//        }
//    }
//
//    public void saveOrDeleteItem(){
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                imageView.setImageResource(R.drawable.starsave);
//                String id =intent.getStringExtra("id");
//                String sectionId =intent.getStringExtra("sectionId");
//                String webPublicationDate = intent.getStringExtra("webPublicationDate");
//                String sectionName = intent.getStringExtra("sectionName");
//                String webTitle = intent.getStringExtra("webTitle");
//                String webUrl = intent.getStringExtra("webUrl");
//                String apiUrl = intent.getStringExtra("apiUrl");
//                String imageLink = intent.getStringExtra("imageLink");
//                Result result = new Result(true,id,sectionId,sectionName,webPublicationDate,webTitle,webUrl,apiUrl,imageLink);
//
//                if(!itemIsSave)
//                {
//                    itemIsSave = true;
//                    db.insertInTheResultsTable(result);
//                    imageView.setImageResource(R.drawable.starsave);
//
//                }
//                else {
//                    itemIsSave = false;
//                    db.deleteInTheResultsTable(id);
//                    imageView.setImageResource(R.drawable.star);
//                }
//            }
//        });
//
//    }
//
//    public void openDialog(Dialog dialog){
//        dialog.setContentView(R.layout.dialog_layout);
//        dialog.show();
//
//    }
//    public void closeDialog(Dialog dialog){
//        dialog.dismiss();
//
//    }

}
