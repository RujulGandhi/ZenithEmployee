package app.com.zenith.Activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

import app.com.zenith.R;
import app.com.zenith.Utils.Constant;
import app.com.zenith.Utils.GetFilePathFromDevice;
import app.com.zenith.Utils.Utils;
import jp.wasabeef.richeditor.RichEditor;

public class Trainingprogram_addpostActivity extends AppCompatActivity {
    // TODO Activity for Training Program ADD POST Page  **** Sanjay Umaraniya *******
    private EditText admin_traingprogram_title;
    private EditText admin_traingprogram_subtitle;
    private EditText admin_traingprogram_subject;
    private EditText admin_traingprogram_content;
    private Button admin_trainingprogram_btnaddpost;
    private RichEditor mEditor;
    public Utils utils;
    public Context context = this;
    private ImageView admin_traingprogram_btn_image;
    private static int PICK_IMAGE_REQUEST = 1;
    private String str_admin_title;
    private String str_admin_subtitle;
    private String str_admin_subject;
    private String str_admin_content;
    private String str_admin_image;
    public Bitmap bitmap;
    public String strUserProfile = "";
    private ImageView postimage_backbtn;
    public final int REQUEST_CODE = 100;
    private String imgDecodableString;
    private static final int SELECT_PICTURE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainingprogram_addpost);
        findViewById();
        mEditor = (RichEditor) findViewById(R.id.admin_traingprogram_content);
        mEditor.setEditorHeight(200);
        mEditor.setEditorFontSize(22);
        mEditor.setEditorFontColor(Color.RED);
        mEditor.setPadding(10, 10, 10, 10);
        mEditor.setPlaceholder("Insert text here...");
        findViewById(R.id.action_undo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.undo();
            }
        });
        findViewById(R.id.action_redo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.redo();
            }
        });
        findViewById(R.id.action_bold).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setBold();
            }
        });
        findViewById(R.id.action_italic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setItalic();
            }
        });
        findViewById(R.id.action_subscript).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setSubscript();
            }
        });
        findViewById(R.id.action_superscript).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setSuperscript();
            }
        });
        findViewById(R.id.action_strikethrough).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setStrikeThrough();
            }
        });
        findViewById(R.id.action_underline).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setUnderline();
            }
        });
        findViewById(R.id.action_heading1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setHeading(1);
            }
        });
        findViewById(R.id.action_heading2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setHeading(2);
            }
        });
        findViewById(R.id.action_heading4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setHeading(4);
            }
        });
        findViewById(R.id.action_heading5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setHeading(5);
            }
        });
        findViewById(R.id.action_heading6).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setHeading(6);
            }
        });
        findViewById(R.id.action_txt_color).setOnClickListener(new View.OnClickListener() {
            private boolean isChanged;

            @Override
            public void onClick(View v) {
                mEditor.setTextColor(isChanged ? Color.BLACK : Color.RED);
                isChanged = !isChanged;
            }
        });
        findViewById(R.id.action_bg_color).setOnClickListener(new View.OnClickListener() {
            private boolean isChanged;

            @Override
            public void onClick(View v) {
                mEditor.setTextBackgroundColor(isChanged ? Color.TRANSPARENT : Color.YELLOW);
                isChanged = !isChanged;
            }
        });

        findViewById(R.id.action_indent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setIndent();
            }
        });
        findViewById(R.id.action_outdent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setOutdent();
            }
        });
        findViewById(R.id.action_align_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setAlignLeft();
            }
        });
        findViewById(R.id.action_align_center).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setAlignCenter();
            }
        });
        findViewById(R.id.action_align_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setAlignRight();
            }
        });
        findViewById(R.id.action_blockquote).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setBlockquote();
            }
        });
        findViewById(R.id.action_insert_bullets).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setBullets();
            }
        });
        findViewById(R.id.action_insert_numbers).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setNumbers();
            }
        });
        findViewById(R.id.action_insert_checkbox).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.insertTodo();
            }
        });

        admin_traingprogram_btn_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImageToServer();
            }
        });

        postimage_backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        admin_trainingprogram_btnaddpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str_admin_title = admin_traingprogram_title.getText().toString().trim();
                str_admin_subtitle = admin_traingprogram_subtitle.getText().toString().trim();
                str_admin_subject = admin_traingprogram_subject.getText().toString().trim();
                str_admin_content = mEditor.toString().trim();
                if (admin_traingprogram_title.getText().toString().trim().length() == 0) {
                    admin_traingprogram_title.setError("Please Enter Title");
                    return;
                } else if (admin_traingprogram_subtitle.getText().toString().trim().length() == 0) {
                    admin_traingprogram_subtitle.setError("Please Enter Sub-title");
                    return;
                } else if (admin_traingprogram_subject.getText().toString().trim().length() == 0) {
                    admin_traingprogram_subject.setError("Please Enter Subject");
                    return;
                } else if (mEditor.toString().trim().length() == 0) {
                    Toast.makeText(context, "Please Enter Content", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    utils = new Utils(context);
                    //http://181.224.157.105/~hirepeop/host2/zenith_coach/api/add_training_program.php?title=abcd&subtitle=xyzz&subject=aa&image=1.jpg&video=1.mp4&content=xyz
                    Log.d("ion_image", "" + imgDecodableString);
                    try {
                        Ion.with(Trainingprogram_addpostActivity.this)
                                .load(Constant.BASE_URL + "add_training_program.php?")
                                .setMultipartParameter("title", URLEncoder.encode(str_admin_title, "UTF-8"))
                                .setMultipartParameter("subtitle", URLEncoder.encode(str_admin_subtitle, "UTF-8"))
                                .setMultipartParameter("subject", URLEncoder.encode(str_admin_subject, "UTF-8"))
                                .setMultipartParameter("content", URLEncoder.encode(str_admin_content, "UTF-8"))
                                .setMultipartFile("file", new File(imgDecodableString))
                                .asString()
                                .setCallback(new FutureCallback<String>() {
                                    @Override
                                    public void onCompleted(Exception e, String result) {
                                        Log.d("JSONRESULT@@" + "", result);
                                    }
                                });
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }

            }

        });
    }

    private void uploadImageToServer() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
        } else {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {

        } else {
            Toast.makeText(Trainingprogram_addpostActivity.this, "Permission is not granted.", Toast.LENGTH_SHORT).show();
        }
    }

    //handling the image chooser activity result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                imgDecodableString = GetFilePathFromDevice.getPath(this, data.getData());
                admin_traingprogram_btn_image.setImageURI(Uri.fromFile(new File(imgDecodableString)));
                Log.d("ImagePath", "" + imgDecodableString);
            }
        }
    }

    /* Get the real path from the URI */
    public String getPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
            Log.d("FINALRESULT", "" + res);
        }
        cursor.close();
        return res;
    }


    private void findViewById() {
        admin_trainingprogram_btnaddpost = (Button) findViewById(R.id.admin_trainingprogram_btnaddpost);
        admin_traingprogram_title = (EditText) findViewById(R.id.admin_traingprogram_title);
        admin_traingprogram_subtitle = (EditText) findViewById(R.id.admin_traingprogram_subtitle);
        admin_traingprogram_subject = (EditText) findViewById(R.id.admin_traingprogram_subject);
        admin_traingprogram_btn_image = (ImageView) findViewById(R.id.admin_traingprogram_btn_image);
        postimage_backbtn = (ImageView) findViewById(R.id.training_addpost_backbutton);
    }

    private class admintrainngaddPost extends AsyncTask<String, String, String> {
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(context);
            pd.setMessage("Loading...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            string_Values();
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("title", str_admin_title);
            hashMap.put("subtitle", str_admin_subtitle);
            hashMap.put("subject", str_admin_subject);
            hashMap.put("image", imgDecodableString);
            hashMap.put("content", str_admin_content);
            String strurl = utils.getResponseofPost(Constant.BASE_URL + "add_training_program.php", hashMap);
            Log.d("Respl", "" + strurl);
            Log.d("IMAgeDecode", "" + imgDecodableString);
            return strurl;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("RESPONSE", "" + s);
            pd.dismiss();
            try {
                JSONObject jsonObject = new JSONObject(s);
                if (jsonObject.getString("status").equalsIgnoreCase("true")) {
                    Toast.makeText(context, "" + jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "" + jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void string_Values() {
        str_admin_title = admin_traingprogram_title.getText().toString().trim();
        str_admin_subtitle = admin_traingprogram_subtitle.getText().toString().trim();
        str_admin_subject = admin_traingprogram_subject.getText().toString().trim();
        str_admin_content = mEditor.toString().trim();
    }
}