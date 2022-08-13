package ru.vk.competition.minbenchmark.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.vk.competition.minbenchmark.entity.SingleQuery;
import ru.vk.competition.minbenchmark.repository.SingleQueryRepository;


@Service
@Slf4j
@RequiredArgsConstructor
public class SingleQueryService {

    private final SingleQueryRepository queryRepository;

    public SingleQuery getAllQueries() {

        return null;
        /*return Mono.fromCallable(queryRepository::findAll)
                .publishOn(Schedulers.boundedElastic())
                .flatMapIterable(x -> x);*/
    }

    public SingleQuery getQueryById(Integer id) {

        return null;
        /*return Mono.fromCallable(() -> queryRepository.findByQueryId(id).orElseThrow(() -> new RuntimeException(
                String.format("Cannot find tableQuery by Id %s", id.toString())
        ))).publishOn(Schedulers.boundedElastic());*/
    }

    public ResponseEntity<Void> deleteQueryById(Integer id) {

        return null;
        /*return Mono.fromCallable(() -> {
            try {
                if(queryRepository.findByQueryId(id).map(SingleQuery::getQueryId).isEmpty()) {
                    return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
                } else {
                    queryRepository.deleteByQueryId(id);
                    return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
                }
            } catch (Exception e) {
                return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
            }
        }).publishOn(Schedulers.boundedElastic());*/
    }

    public ResponseEntity<Void> addQueryWithQueryId(SingleQuery singleQuery) {

        return null;
        /*return Mono.fromCallable(() -> {
            queryRepository.save(singleQuery);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        }).publishOn(Schedulers.boundedElastic());*/
    }

    public ResponseEntity<Void> updateQueryWithQueryId(SingleQuery singleQuery) {

        return null;
        /*return Mono.fromCallable(() -> {
            queryRepository.findByQueryId(singleQuery.getQueryId())
                    .orElseThrow(() -> new RuntimeException(
                            String.format("Cannot find tableQuery by ID %s", singleQuery.getQueryId())
                    ));
            queryRepository.save(singleQuery);
            return ResponseEntity.<Void>ok(null);
        }).publishOn(Schedulers.boundedElastic());*/
    }

    public ResponseEntity<Void> executeSingleQuery(Integer id) {


        return null;
        /*return Mono.fromCallable(() -> {
            Connection connection = null;
            Statement statement = null;
            Optional<String> createSql = null;
            try {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/postgres",
                        "postgres",
                        "123"
                );
                log.debug("Database connected hahaha....");
                statement = connection.createStatement();
                createSql = queryRepository.findByQueryId(id).map(SingleQuery::getQuery);
                statement.execute(createSql.get());
                statement.close();
                connection.close();
                return new ResponseEntity<Void>(HttpStatus.CREATED);
            } catch (Exception e) {
                System.err.println( e.getClass().getName()+": "+ e.getMessage() );
                return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
            }
        }).publishOn(Schedulers.boundedElastic());*/
    }
}