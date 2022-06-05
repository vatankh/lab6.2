package commands;

import Control.Storage;

import java.io.Serializable;

public abstract class AbsCommand implements Serializable {
    Storage storage;
    String messege="command worked";
    private static final long serialVersionUID = -4507489610617393544L;

    public abstract String work() throws Exception;
    public void loadStorage(Storage storage){
        this.storage=storage;
    }
}
