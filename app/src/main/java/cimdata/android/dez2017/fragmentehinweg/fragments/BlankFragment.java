package cimdata.android.dez2017.fragmentehinweg.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cimdata.android.dez2017.fragmentehinweg.R;
import cimdata.android.dez2017.fragmentehinweg.activities.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment {


    int counter;

    public static final String ARGS_STRING_COUNTER = "args.int.counter";
    TextView outputText;

    public BlankFragment() {
        // Required empty public constructor
    }

    public static BlankFragment newInstance (int counter) {

        BlankFragment fragment = new BlankFragment(); // 1. Eine Instanz des Fragments erstellen

        Bundle args = new Bundle();                   // 2. ein neues Bundle erstellen
        args.putInt(ARGS_STRING_COUNTER, counter);    // 3. die Werte dem Bundle hinzufügen
        fragment.setArguments(args);                  // 4. das Bundle dem Fragment hinzufügen

        return fragment;                              // 5. Das Fragment mit den Argumenten zurückgeben
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Um die Referenzen nach dem inflate() und vor dem return zu holen, müssen wir
        // den Ausdruck splitten.
        View view = inflater.inflate(R.layout.fragment_blank, container, false);;

        // Hier können wir auf das Layout zugreifen
        outputText = view.findViewById(R.id.txt_fr_blank_output);
        outputText.setText(String.valueOf(counter));

        return view;
    }

    // Hier können wir schon die Werte aus den arguments abholen
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hier holen wir unsere Argument wieder aus dem Fragment mit Hilfe des Keys
        Bundle arguments = getArguments();

        if (arguments != null) {

//          counter = arguments.getInt(MainActivity.ARGS_FRAGMENT_COUNTER, -1); // Variante 1 (mit dem Bundle aus der MainActivity)
            counter = arguments.getInt(ARGS_STRING_COUNTER, -1); // Variante 2 (mit der Factory)
        } else {
            counter = -1;
        }

    }
}
