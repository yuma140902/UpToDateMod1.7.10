package yuma140902.uptodatemod.launch.organize;

import java.io.IOException;
import java.util.List;
import yuma140902.uptodatemod.launch.model.Copy;

public interface IOrganizer {
	void organize(List<Copy> copies) throws IOException;
}
