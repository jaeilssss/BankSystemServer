package com.example.banksystembatch.job;

import com.example.banksystembatch.entity.Account;
import com.example.banksystembatch.entity.AccountHistory;
import com.example.banksystembatch.repository.AccountRepository;
import com.example.banksystembatch.service.InterestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import java.util.Arrays;
import java.util.Collections;

@Configuration
@RequiredArgsConstructor
@Slf4j
@EnableBatchProcessing
public class InterestJobConfig {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final InterestService interestService;
    @Bean
    public Job interestJob(Step interestStep) {
        return jobBuilderFactory.get("interestJob")
                .incrementer(new RunIdIncrementer())
                .start(interestStep)
                .build();
    }

    @JobScope
    @Bean
    public Step interestStep(
            ItemReader<Account> accountItemReader,
            ItemProcessor<Account, AccountHistory> interestItemProcessor,
            ItemWriter<AccountHistory> interestWriter
    ) {
        return stepBuilderFactory
                .get("interestStep")
                .<Account, AccountHistory>chunk(10)
                .reader(accountItemReader)
                .processor(interestItemProcessor)
                .writer(interestWriter)
                .build();
    }

    @StepScope
    @Bean
    public ItemReader<Account> accountItemReader(AccountRepository accountRepository
    ){
        return new RepositoryItemReaderBuilder<Account>()
                .name("interestRepositoryItemReader")
                .repository(accountRepository)
                .methodName("findAll")
                .pageSize(10)
                .arguments(Arrays.asList())
                .sorts(Collections.singletonMap("id", Sort.Direction.ASC))
                .build();
    }

    @StepScope
    @Bean
    public ItemWriter<AccountHistory> interestItemWriter() {
        return items -> {
            items.forEach(interestService::saveAccountHistory);
        };
    }

    @StepScope
    @Bean
    public ItemProcessor<Account, AccountHistory> interestItemProcessor() {
        return account -> {
            double rate = account.getDeposit().getInterestRate() / 100;
            int totalInterest = (int) (account.getTotalDeposit()*rate + account.getTotalDeposit());
            account.setTotalDeposit(totalInterest);
            return account.createAccountHistoryForInterest((int)(account.getTotalDeposit()*rate));
        };
    }

}
