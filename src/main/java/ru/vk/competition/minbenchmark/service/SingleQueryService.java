package ru.vk.competition.minbenchmark.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vk.competition.minbenchmark.dto.SingleQueryDto;
import ru.vk.competition.minbenchmark.entity.SingleQueryEntity;
import ru.vk.competition.minbenchmark.mapper.SingleQueryMapper;
import ru.vk.competition.minbenchmark.repository.SingleQueryRepository;
import ru.vk.competition.minbenchmark.repository.TableRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SingleQueryService {
    TableRepository tableRepository;
    SingleQueryRepository singleQueryRepository;
    SingleQueryMapper singleQueryMapper;

    public void saveSingleQuery(SingleQueryDto singleQuery) {
        singleQueryRepository.save(singleQueryMapper.toEntity(singleQuery));
    }

    public void modifySingleQuery(SingleQueryDto singleQuery) {
        singleQueryRepository.findById(singleQuery.getQueryId())
                .ifPresentOrElse(singleQueryEntity -> {
                            singleQueryEntity.setQuery(singleQuery.getQuery());
                            singleQueryRepository.save(singleQueryEntity);
                        },
                        () -> {
                            throw new IllegalArgumentException("Запроса с таким id не существует");
                        });
    }

    public void deleteSingleQuery(Integer id) {
        singleQueryRepository.findById(id)
                .ifPresentOrElse(singleQueryRepository::delete, () -> {
                    throw new IllegalArgumentException("Запроса с таким id не существует");
                });
    }

    public void executeSingleQuery(Integer id) {
        singleQueryRepository.findById(id)
                .ifPresentOrElse(singleQueryEntity -> {
                            try {
                                tableRepository.executeQuery(singleQueryEntity.getQuery());
                            } catch (Exception ex) {
                                throw new RuntimeException("Синтаксис запроса неверный");
                            }
                        },
                        () -> {
                            throw new IllegalArgumentException("Запроса с таким id не существует");
                        });
    }

    public SingleQueryDto getSingleQueryById(Integer id) {
        Optional<SingleQueryEntity> singleQueryEntityOpt = singleQueryRepository.findById(id);
        if (singleQueryEntityOpt.isEmpty())
            throw new IllegalArgumentException("Запроса с таким id не существует");
        return singleQueryMapper.toDto(singleQueryEntityOpt.get());
    }

    public List<SingleQueryDto> getAllSingleQueries() {
        return singleQueryMapper.toDto(singleQueryRepository.findAll());
    }
}