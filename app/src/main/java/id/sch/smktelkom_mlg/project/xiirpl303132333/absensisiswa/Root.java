package id.sch.smktelkom_mlg.project.xiirpl303132333.absensisiswa;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Lia on 19/11/2016.
 */

public class Root extends Fragment
{
    public Root(){}
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        rootView = inflater.inflate(R.layout.root, container, false);
        getActivity().setTitle("Absensi Siswa");
        return rootView;
    }
}
