package ddsutn.Business.SchedulerRecomendaciones;

import org.springframework.beans.factory.annotation.Autowired;

public class JavaJob {

	@Autowired
	private RecomendacionesService jobService;

	public void jobWithDelay() {
		jobService.ejecutar();
	}

}
