package isfaaghyth.app.mahasiswaparsing;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by isfaaghyth on 11/24/17.
 * github: @isfaaghyth
 */

public class GetDataTask extends AsyncTask<Void,Void,Void> {

    private String url;
    private ArrayList<HashMap<String, String>> datas;

    public GetDataTask(String url, ArrayList<HashMap<String, String>> datas) {
        this.url = url;
        this.datas = datas;
    }

    public ArrayList<HashMap<String, String>> getDatas() {
        return datas;
    }

    @Override protected Void doInBackground(Void... params) {
        HttpHandler hd = new HttpHandler();
        String resultJson = hd.makeServiceCall(url); //ini json secara keseluruhan
        try {
            //secara default, json itu diawali dengan object {}
            JSONObject root = new JSONObject(resultJson);
            //kita ambil jsonArray dari root nya `mahasiswa`
            JSONArray mahasiswa = root.getJSONArray("mahasiswa");

            for (int i=0; i < mahasiswa.length(); i++) {
                HashMap<String, String> dataMhs = new HashMap<>();
                JSONObject mhs = mahasiswa.getJSONObject(i);

                String nama = mhs.getString("nama");
                String nim = mhs.getString("nim");
                String prodi = mhs.getString("prodi");

                dataMhs.put("nama", nama);
                dataMhs.put("nim", nim);
                dataMhs.put("prodi", prodi);

                Log.d("TAG", nama+"\n"+nim);

                datas.add(dataMhs);
            }

        } catch (Exception e) {
            Log.e("err:", e.getMessage());
        }
        return null;
    }

}
