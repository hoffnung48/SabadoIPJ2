package mx.com.eonsoft.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private Button boton1,boton2,botonImagen;
    private TextView texto;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boton1 = findViewById(R.id.button);
        boton2 = findViewById(R.id.button2);
        botonImagen = findViewById(R.id.button3);
        texto = findViewById(R.id.textView);
        image = findViewById(R.id.imageView);


        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                texto.setText("Hola GitHub");
                Toast.makeText(MainActivity.this,"Se cambio el mensaje en el textView",Toast.LENGTH_SHORT).show();

            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                texto.setText("Diplomado Android");

            }
        });

        botonImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url ="https://cdn.toontownrewritten.com/news-site/img/17-2-5_uncleflippy.jpg?2";
                try{
                    new DescargarImagen().execute(new URL(url));
                }catch (Exception ex){
                    Log.e("diplo tag","Error al descargar la imagen");
                }
            }
        });
    }


    class DescargarImagen extends AsyncTask<URL,Integer,Bitmap> {

        private Bitmap imagenDescargada;

        @Override
        protected Bitmap doInBackground(URL... urls) {
            try{
                Log.i("diplo tag","DescargandoImagen");
                imagenDescargada = BitmapFactory.decodeStream(urls[0].openConnection().getInputStream());
            }catch(Exception ex){
                Log.e("internet error",ex.getMessage());
            }
            return imagenDescargada;
        }

        @Override
        protected void onProgressUpdate(Integer ... values){
            super.onProgressUpdate(values[0]);
            Log.i("diplo tag",""+values[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap){
            image.setImageBitmap(imagenDescargada);
            super.onPostExecute(bitmap);
        }
    }
}
