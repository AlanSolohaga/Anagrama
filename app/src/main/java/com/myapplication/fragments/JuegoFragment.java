package com.myapplication.fragments;

import android.animation.Animator;
import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.myapplication.R;
import com.myapplication.modelo.Anagrama;

import java.util.ArrayList;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link JuegoFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class JuegoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment JuegoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static JuegoFragment newInstance(String param1, String param2) {
        JuegoFragment fragment = new JuegoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public JuegoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    TextView frase;
    EditText fraseIngresada;
    Button btnValidar;
    FrameLayout frame;
    ArrayList<Anagrama> anagramas = new ArrayList();
    int valor=0;
    Handler handler;
    LottieAnimationView animacion;
    MediaPlayer mp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_juego, container, false);
        frase = view.findViewById(R.id.textFrase);
        fraseIngresada = view.findViewById(R.id.editIngreso);

        btnValidar = view.findViewById(R.id.btnValidar);
        btnValidar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validar();
            }
        });
        animacion = view.findViewById(R.id.animacion);
        iniciar();
        return view;
    }

    private void validar() {
        if(fraseIngresada.getText().toString().toUpperCase().equalsIgnoreCase(anagramas.get(valor).getFrase().toString())){
            animacion.setAnimation("correct.json");
            animacion.loop(false);
            animacion.playAnimation();
            mp = MediaPlayer.create(getContext(),R.raw.victory);
            mp.start();
            frase.setText(""+anagramas.get(valor).getFrase());

//            Toast.makeText(getContext(),"CORRECTO!",Toast.LENGTH_LONG).show();
            fraseIngresada.setText("");
            ejecutarHilo();

        }else{
            Toast.makeText(getContext(),"INCORRECTO!",Toast.LENGTH_LONG).show();
            fraseIngresada.setText("");
        }
    }

    private void ejecutarHilo() {
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                animacion.stopNestedScroll();
                valor++;
                agregarFrase();
                handler.postDelayed(this,5000);
            }
        },5000);
    }



    private void iniciar() {
        cargar();
        agregarFrase();
    }

    private void agregarFrase() {
        if(valor<anagramas.size()){
        frase.setText(anagramas.get(valor).getAnagrama());
        }else{
            //NO HAY MAS FRASES
        }
    }

    private void cargar() {
        Anagrama anagrama = new Anagrama("RIESGO Y VALORA EUFRON A PAGAR",
                "SERGIO Y ALVARO FUERON A PRAGA",0);
        Anagrama anagrama1 = new Anagrama("SALARIO SE DE AIRES",
                "ROSALÍA ES DE ARIES",1);
        anagramas.add(anagrama);
        anagramas.add(anagrama1);

    }


}
