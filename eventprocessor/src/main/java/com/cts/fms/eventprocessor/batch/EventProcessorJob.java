package com.cts.fms.eventprocessor.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.cts.fms.eventprocessor.entity.EventInfo;
import com.cts.fms.eventprocessor.entity.EventSummary;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EventProcessorJob extends JobExecutionListenerSupport {
	
	private static final Logger logger = LoggerFactory.getLogger(EventProcessorJob.class);
	
	@Autowired
	JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	StepBuilderFactory stepBuilderFactory;
	
	@Value("${input.outreachEventSummaryfile}")
	private Resource outreachEventSummaryCsvResource;
	
	@Value("${input.outreachEventInfofile}")
	private Resource outreachEventInfoCsvResource;
	
	@Autowired
	EventSummaryWriter eventSummaryWriter;
	
	@Autowired
	EventInfoWriter eventInfoWriter;
	
	@Bean(name = "eventJob")
	public Job eventProcessorJob() {
		
		Step eventSummaryStep = stepBuilderFactory.get("event-summary-file-to-db")
				.<EventSummary, EventSummary>chunk(5)
				.reader(new EventSummaryReader(outreachEventSummaryCsvResource))
				.writer(eventSummaryWriter).build();
		
		Step eventInfoStep = stepBuilderFactory.get("event-info-file-to-db")
				.<EventInfo, EventInfo>chunk(5)
				.reader(new EventInfoReader(outreachEventInfoCsvResource))
				.writer(eventInfoWriter).build();
		
		Job job = jobBuilderFactory.get("eventProcessorJob")
				.incrementer(new RunIdIncrementer())
				.listener(this)
				.start(eventSummaryStep)
				.next(eventInfoStep)
				.build();

		return job;
	}
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("BATCH JOB COMPLETED SUCCESSFULLY");
		}
	}

}
