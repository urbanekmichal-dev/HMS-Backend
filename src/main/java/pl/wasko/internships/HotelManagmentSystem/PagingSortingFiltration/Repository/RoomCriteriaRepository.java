package pl.wasko.internships.HotelManagmentSystem.PagingSortingFiltration.Repository;


import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import pl.wasko.internships.HotelManagmentSystem.Entities.RoomEntity;
import pl.wasko.internships.HotelManagmentSystem.PagingSortingFiltration.model.RoomPage;
import pl.wasko.internships.HotelManagmentSystem.PagingSortingFiltration.model.RoomSearchCriteria;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Repository
public class RoomCriteriaRepository {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public RoomCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<RoomEntity> findAllWithFilters(RoomPage roomPage, RoomSearchCriteria roomSearchCriteria) {
        CriteriaQuery<RoomEntity> criteriaQuery = criteriaBuilder.createQuery(RoomEntity.class);
        Root<RoomEntity> roomEntityRoot = criteriaQuery.from(RoomEntity.class);
        Predicate predicate = getPredicate(roomSearchCriteria, roomEntityRoot);
        criteriaQuery.where(predicate);
        setOrder(roomPage, criteriaQuery, roomEntityRoot);

        TypedQuery<RoomEntity> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(roomPage.getPageNumber() * roomPage.getPageSize());
        typedQuery.setMaxResults(roomPage.getPageSize());

        Pageable pagable = getPagable(roomPage);
        long roomsCount = getRoomsCount(predicate);
        return new PageImpl<>(typedQuery.getResultList(), pagable, roomsCount);
    }

    private long getRoomsCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<RoomEntity> countRoot = countQuery.from(RoomEntity.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }


    private Pageable getPagable(RoomPage roomPage) {
        Sort sort = Sort.by(roomPage.getSortDirection(), roomPage.getSortBy());
        return PageRequest.of(roomPage.getPageNumber(), roomPage.getPageSize(), sort);

    }


    private void setOrder(RoomPage roomPage, CriteriaQuery<RoomEntity> criteriaQuery, Root<RoomEntity> roomEntityRoot) {
        if (roomPage.getSortDirection().equals(Sort.Direction.ASC)) {
            criteriaQuery.orderBy(criteriaBuilder.asc(roomEntityRoot.get(roomPage.getSortBy())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(roomEntityRoot.get(roomPage.getSortBy())));
        }
    }


    private Predicate getPredicate(RoomSearchCriteria roomSearchCriteria, Root<RoomEntity> roomEntityRoot) {
        List<Predicate> predicateList = new ArrayList<>();
        if (Objects.nonNull(roomSearchCriteria.getFloor())) {
            predicateList.add(
                    criteriaBuilder.equal(roomEntityRoot.get("floor"),roomSearchCriteria.getFloor())
                   // criteriaBuilder.like(roomEntityRoot.get("floor"),  roomSearchCriteria.getFloor() + "%")
            );
        }
        if (Objects.nonNull(roomSearchCriteria.getRoomType())) {
            predicateList.add(
                    criteriaBuilder.like(roomEntityRoot.get("roomType"), "%" + roomSearchCriteria.getRoomType() + "%")
            );
        }
        return criteriaBuilder.and(predicateList.toArray(new Predicate[0]));
    }

}
