package tk.julianjurec.linuxsession14.Agenda;

import tk.julianjurec.linuxsession14.Base.BaseFragment;
import tk.julianjurec.linuxsession14.Base.BasePresenter;

/**
 * Created by sp0rk on 22.03.17.
 */

public interface AgendaContract {
    interface View extends BaseFragment<AgendaPresenter> {
        void showToast(String test);
    }

    interface Presenter extends BasePresenter {

    }
}
