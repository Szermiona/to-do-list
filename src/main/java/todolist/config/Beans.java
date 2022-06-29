package todolist.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import todolist.entity.TaskDAO;
import todolist.repository.TaskRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Configuration
public class Beans {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public TaskRepository taskRepository() {
        return new TaskRepository() {
            @Override
            public List<TaskDAO> findAll() {
                return null;
            }

            @Override
            public List<TaskDAO> findAll(Sort sort) {
                return null;
            }

            @Override
            public List<TaskDAO> findAllById(Iterable<UUID> uuids) {
                return null;
            }

            @Override
            public <S extends TaskDAO> List<S> saveAll(Iterable<S> entities) {
                return null;
            }

            @Override
            public void flush() {

            }

            @Override
            public <S extends TaskDAO> S saveAndFlush(S entity) {
                return null;
            }

            @Override
            public <S extends TaskDAO> List<S> saveAllAndFlush(Iterable<S> entities) {
                return null;
            }

            @Override
            public void deleteAllInBatch(Iterable<TaskDAO> entities) {

            }

            @Override
            public void deleteAllByIdInBatch(Iterable<UUID> uuids) {

            }

            @Override
            public void deleteAllInBatch() {

            }

            @Override
            public TaskDAO getOne(UUID uuid) {
                return null;
            }

            @Override
            public TaskDAO getById(UUID uuid) {
                return null;
            }

            @Override
            public TaskDAO getReferenceById(UUID uuid) {
                return null;
            }

            @Override
            public <S extends TaskDAO> List<S> findAll(Example<S> example) {
                return null;
            }

            @Override
            public <S extends TaskDAO> List<S> findAll(Example<S> example, Sort sort) {
                return null;
            }

            @Override
            public Page<TaskDAO> findAll(Pageable pageable) {
                return null;
            }

            @Override
            public <S extends TaskDAO> S save(S entity) {
                return null;
            }

            @Override
            public Optional<TaskDAO> findById(UUID uuid) {
                return Optional.empty();
            }

            @Override
            public boolean existsById(UUID uuid) {
                return false;
            }

            @Override
            public long count() {
                return 0;
            }

            @Override
            public void deleteById(UUID uuid) {

            }

            @Override
            public void delete(TaskDAO entity) {

            }

            @Override
            public void deleteAllById(Iterable<? extends UUID> uuids) {

            }

            @Override
            public void deleteAll(Iterable<? extends TaskDAO> entities) {

            }

            @Override
            public void deleteAll() {

            }

            @Override
            public <S extends TaskDAO> Optional<S> findOne(Example<S> example) {
                return Optional.empty();
            }

            @Override
            public <S extends TaskDAO> Page<S> findAll(Example<S> example, Pageable pageable) {
                return null;
            }

            @Override
            public <S extends TaskDAO> long count(Example<S> example) {
                return 0;
            }

            @Override
            public <S extends TaskDAO> boolean exists(Example<S> example) {
                return false;
            }

            @Override
            public <S extends TaskDAO, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
                return null;
            }
        };
    }
}
