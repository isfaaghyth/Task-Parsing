package isfaaghyth.app.mahasiswaparsing;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    /*
    * URL untuk memanggil json
    * @url: 10.0.2.2 = jika di debug melalui emulator
    * @url: 192.168.**.** = gunakan ip lokal jika di debug melalui device
    * */
    private final static String URL = "http://10.0.2.2/moprojson/getdata.php";
    private ArrayList<HashMap<String, String>> datas; //menampung data dari json

    private AlertDialog dialog;
    private ListView lstData;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lstData = (ListView) findViewById(R.id.lst_data); //casting
        datas = new ArrayList<>();
        doRequest();
    }

    private void showDialog(String message) {
        //kalau misal dialog nya belum di inisialisasi, maka lakukan inisialisasi
        if (dialog == null) dialog = new AlertDialog.Builder(this).create();
        dialog.setMessage(message);
        dialog.setCancelable(false);
        dialog.show();
    }

    private void hideDialog() {
        //cek apakah dialognya masih muncul atau tidak, klau masih, maka ...
        if (dialog.isShowing()) {
            //tutup
            dialog.dismiss();
        }
    }

    private void doRequest() {
        new GetDataTask(URL, datas) {
            @Override protected void onPreExecute() {
                super.onPreExecute();
                showDialog("Silahkan Tunggu...");
            }
            @Override protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                hideDialog();
                //adapter untuk listview dari data `datas` yang sudah di get dari `getDataTask`
                ListAdapter adapter = new SimpleAdapter(
                        MainActivity.this,
                        getDatas(),
                        R.layout.layout_item, new String[]{"nama", "prodi"},
                        new int[]{R.id.txt_nama, R.id.txt_nim}
                );
                lstData.setAdapter(adapter);
            }
        }.execute();
    }

}
