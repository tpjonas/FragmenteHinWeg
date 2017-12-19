package cimdata.android.dez2017.fragmentehinweg.activities;

import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cimdata.android.dez2017.fragmentehinweg.R;
import cimdata.android.dez2017.fragmentehinweg.fragments.BlankFragment;

public class MainActivity extends AppCompatActivity {

    public static final String ARGS_FRAGMENT_COUNTER = "args.fragment.counter";

    private Button addFragmentButton;
    private Button removeFragmentButton;

    private int fragmentContainerId = R.id.ly_acmain_container_fragment;
    private int counter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addFragmentButton = findViewById(R.id.btn_acmain_add_fragment);
        removeFragmentButton = findViewById(R.id.btn_acmain_remove_fragment);

        addFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment();
            }
        });

        removeFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFragment();
            }
        });
    }

    private void addFragment() {

        counter += 1;

        // Variante 1: Mit bundle aus der Activity
        /*
        BlankFragment fragment = new BlankFragment();

        // Daten die von der MainActivity zu dem Fragment geschickt werden,
        // werden in ein Bundle gepackt ...
        Bundle arguments = new Bundle();
        arguments.putInt(ARGS_FRAGMENT_COUNTER, counter);

        // ... und and das Fragment hängen.
        fragment.setArguments(arguments);
        */


        // Variante 2: Mit der Factory
        // Vorteil: Wir brauchen in der MainActivity keine Konstante (Key) für das Fragment speichern
        // Dadurch können wir das Fragment auch leicht in anderen Activities verwenden
        BlankFragment fragment = BlankFragment.newInstance(counter);

        FragmentManager manager = getSupportFragmentManager();

        manager
                .beginTransaction()
                //.addToBackStack(null) // Mit diesem Befehl können wir die Activities über den Back-Button löschen
                .add(fragmentContainerId, fragment)
                .commit();


    }

    private void removeFragment() {


        // Um an unser Fragment zu gelangen müssen wir über den Manager gehen.
        // Es ist nicht ratsam sich einfach einen Referenz zu dem Fragment zu
        // speichern. Diese kann unter Umständen verloren gehen.
        FragmentManager manager = getSupportFragmentManager();

        // Um auf das Fragment zugreifen zu können, suchen wir es innerhalb des Containers,
        // den wir über die ID ansprechen. (Der Name der Methode ist ein wenig verwirrend.)
        BlankFragment fragment = (BlankFragment) manager.findFragmentById(fragmentContainerId);

        // Wenn der Container ein Fragment enthält, löschen wir es.
        if (fragment != null) {

            counter -= 1;

            manager
                    .beginTransaction()
                    .remove(fragment)
                    .commit();
        }
    }
}
