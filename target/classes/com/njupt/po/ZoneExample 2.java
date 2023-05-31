package com.njupt.po;

import java.util.ArrayList;
import java.util.List;

public class ZoneExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ZoneExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andZoneIdIsNull() {
            addCriterion("zone_id is null");
            return (Criteria) this;
        }

        public Criteria andZoneIdIsNotNull() {
            addCriterion("zone_id is not null");
            return (Criteria) this;
        }

        public Criteria andZoneIdEqualTo(Integer value) {
            addCriterion("zone_id =", value, "zoneId");
            return (Criteria) this;
        }

        public Criteria andZoneIdNotEqualTo(Integer value) {
            addCriterion("zone_id <>", value, "zoneId");
            return (Criteria) this;
        }

        public Criteria andZoneIdGreaterThan(Integer value) {
            addCriterion("zone_id >", value, "zoneId");
            return (Criteria) this;
        }

        public Criteria andZoneIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("zone_id >=", value, "zoneId");
            return (Criteria) this;
        }

        public Criteria andZoneIdLessThan(Integer value) {
            addCriterion("zone_id <", value, "zoneId");
            return (Criteria) this;
        }

        public Criteria andZoneIdLessThanOrEqualTo(Integer value) {
            addCriterion("zone_id <=", value, "zoneId");
            return (Criteria) this;
        }

        public Criteria andZoneIdIn(List<Integer> values) {
            addCriterion("zone_id in", values, "zoneId");
            return (Criteria) this;
        }

        public Criteria andZoneIdNotIn(List<Integer> values) {
            addCriterion("zone_id not in", values, "zoneId");
            return (Criteria) this;
        }

        public Criteria andZoneIdBetween(Integer value1, Integer value2) {
            addCriterion("zone_id between", value1, value2, "zoneId");
            return (Criteria) this;
        }

        public Criteria andZoneIdNotBetween(Integer value1, Integer value2) {
            addCriterion("zone_id not between", value1, value2, "zoneId");
            return (Criteria) this;
        }

        public Criteria andZoneNameIsNull() {
            addCriterion("zone_name is null");
            return (Criteria) this;
        }

        public Criteria andZoneNameIsNotNull() {
            addCriterion("zone_name is not null");
            return (Criteria) this;
        }

        public Criteria andZoneNameEqualTo(String value) {
            addCriterion("zone_name =", value, "zoneName");
            return (Criteria) this;
        }

        public Criteria andZoneNameNotEqualTo(String value) {
            addCriterion("zone_name <>", value, "zoneName");
            return (Criteria) this;
        }

        public Criteria andZoneNameGreaterThan(String value) {
            addCriterion("zone_name >", value, "zoneName");
            return (Criteria) this;
        }

        public Criteria andZoneNameGreaterThanOrEqualTo(String value) {
            addCriterion("zone_name >=", value, "zoneName");
            return (Criteria) this;
        }

        public Criteria andZoneNameLessThan(String value) {
            addCriterion("zone_name <", value, "zoneName");
            return (Criteria) this;
        }

        public Criteria andZoneNameLessThanOrEqualTo(String value) {
            addCriterion("zone_name <=", value, "zoneName");
            return (Criteria) this;
        }

        public Criteria andZoneNameLike(String value) {
            addCriterion("zone_name like", value, "zoneName");
            return (Criteria) this;
        }

        public Criteria andZoneNameNotLike(String value) {
            addCriterion("zone_name not like", value, "zoneName");
            return (Criteria) this;
        }

        public Criteria andZoneNameIn(List<String> values) {
            addCriterion("zone_name in", values, "zoneName");
            return (Criteria) this;
        }

        public Criteria andZoneNameNotIn(List<String> values) {
            addCriterion("zone_name not in", values, "zoneName");
            return (Criteria) this;
        }

        public Criteria andZoneNameBetween(String value1, String value2) {
            addCriterion("zone_name between", value1, value2, "zoneName");
            return (Criteria) this;
        }

        public Criteria andZoneNameNotBetween(String value1, String value2) {
            addCriterion("zone_name not between", value1, value2, "zoneName");
            return (Criteria) this;
        }

        public Criteria andZoneCodeIsNull() {
            addCriterion("zone_code is null");
            return (Criteria) this;
        }

        public Criteria andZoneCodeIsNotNull() {
            addCriterion("zone_code is not null");
            return (Criteria) this;
        }

        public Criteria andZoneCodeEqualTo(String value) {
            addCriterion("zone_code =", value, "zoneCode");
            return (Criteria) this;
        }

        public Criteria andZoneCodeNotEqualTo(String value) {
            addCriterion("zone_code <>", value, "zoneCode");
            return (Criteria) this;
        }

        public Criteria andZoneCodeGreaterThan(String value) {
            addCriterion("zone_code >", value, "zoneCode");
            return (Criteria) this;
        }

        public Criteria andZoneCodeGreaterThanOrEqualTo(String value) {
            addCriterion("zone_code >=", value, "zoneCode");
            return (Criteria) this;
        }

        public Criteria andZoneCodeLessThan(String value) {
            addCriterion("zone_code <", value, "zoneCode");
            return (Criteria) this;
        }

        public Criteria andZoneCodeLessThanOrEqualTo(String value) {
            addCriterion("zone_code <=", value, "zoneCode");
            return (Criteria) this;
        }

        public Criteria andZoneCodeLike(String value) {
            addCriterion("zone_code like", value, "zoneCode");
            return (Criteria) this;
        }

        public Criteria andZoneCodeNotLike(String value) {
            addCriterion("zone_code not like", value, "zoneCode");
            return (Criteria) this;
        }

        public Criteria andZoneCodeIn(List<String> values) {
            addCriterion("zone_code in", values, "zoneCode");
            return (Criteria) this;
        }

        public Criteria andZoneCodeNotIn(List<String> values) {
            addCriterion("zone_code not in", values, "zoneCode");
            return (Criteria) this;
        }

        public Criteria andZoneCodeBetween(String value1, String value2) {
            addCriterion("zone_code between", value1, value2, "zoneCode");
            return (Criteria) this;
        }

        public Criteria andZoneCodeNotBetween(String value1, String value2) {
            addCriterion("zone_code not between", value1, value2, "zoneCode");
            return (Criteria) this;
        }

        public Criteria andServerUrlIsNull() {
            addCriterion("server_url is null");
            return (Criteria) this;
        }

        public Criteria andServerUrlIsNotNull() {
            addCriterion("server_url is not null");
            return (Criteria) this;
        }

        public Criteria andServerUrlEqualTo(String value) {
            addCriterion("server_url =", value, "serverUrl");
            return (Criteria) this;
        }

        public Criteria andServerUrlNotEqualTo(String value) {
            addCriterion("server_url <>", value, "serverUrl");
            return (Criteria) this;
        }

        public Criteria andServerUrlGreaterThan(String value) {
            addCriterion("server_url >", value, "serverUrl");
            return (Criteria) this;
        }

        public Criteria andServerUrlGreaterThanOrEqualTo(String value) {
            addCriterion("server_url >=", value, "serverUrl");
            return (Criteria) this;
        }

        public Criteria andServerUrlLessThan(String value) {
            addCriterion("server_url <", value, "serverUrl");
            return (Criteria) this;
        }

        public Criteria andServerUrlLessThanOrEqualTo(String value) {
            addCriterion("server_url <=", value, "serverUrl");
            return (Criteria) this;
        }

        public Criteria andServerUrlLike(String value) {
            addCriterion("server_url like", value, "serverUrl");
            return (Criteria) this;
        }

        public Criteria andServerUrlNotLike(String value) {
            addCriterion("server_url not like", value, "serverUrl");
            return (Criteria) this;
        }

        public Criteria andServerUrlIn(List<String> values) {
            addCriterion("server_url in", values, "serverUrl");
            return (Criteria) this;
        }

        public Criteria andServerUrlNotIn(List<String> values) {
            addCriterion("server_url not in", values, "serverUrl");
            return (Criteria) this;
        }

        public Criteria andServerUrlBetween(String value1, String value2) {
            addCriterion("server_url between", value1, value2, "serverUrl");
            return (Criteria) this;
        }

        public Criteria andServerUrlNotBetween(String value1, String value2) {
            addCriterion("server_url not between", value1, value2, "serverUrl");
            return (Criteria) this;
        }

        public Criteria andIdentityUrlIsNull() {
            addCriterion("identity_url is null");
            return (Criteria) this;
        }

        public Criteria andIdentityUrlIsNotNull() {
            addCriterion("identity_url is not null");
            return (Criteria) this;
        }

        public Criteria andIdentityUrlEqualTo(String value) {
            addCriterion("identity_url =", value, "identityUrl");
            return (Criteria) this;
        }

        public Criteria andIdentityUrlNotEqualTo(String value) {
            addCriterion("identity_url <>", value, "identityUrl");
            return (Criteria) this;
        }

        public Criteria andIdentityUrlGreaterThan(String value) {
            addCriterion("identity_url >", value, "identityUrl");
            return (Criteria) this;
        }

        public Criteria andIdentityUrlGreaterThanOrEqualTo(String value) {
            addCriterion("identity_url >=", value, "identityUrl");
            return (Criteria) this;
        }

        public Criteria andIdentityUrlLessThan(String value) {
            addCriterion("identity_url <", value, "identityUrl");
            return (Criteria) this;
        }

        public Criteria andIdentityUrlLessThanOrEqualTo(String value) {
            addCriterion("identity_url <=", value, "identityUrl");
            return (Criteria) this;
        }

        public Criteria andIdentityUrlLike(String value) {
            addCriterion("identity_url like", value, "identityUrl");
            return (Criteria) this;
        }

        public Criteria andIdentityUrlNotLike(String value) {
            addCriterion("identity_url not like", value, "identityUrl");
            return (Criteria) this;
        }

        public Criteria andIdentityUrlIn(List<String> values) {
            addCriterion("identity_url in", values, "identityUrl");
            return (Criteria) this;
        }

        public Criteria andIdentityUrlNotIn(List<String> values) {
            addCriterion("identity_url not in", values, "identityUrl");
            return (Criteria) this;
        }

        public Criteria andIdentityUrlBetween(String value1, String value2) {
            addCriterion("identity_url between", value1, value2, "identityUrl");
            return (Criteria) this;
        }

        public Criteria andIdentityUrlNotBetween(String value1, String value2) {
            addCriterion("identity_url not between", value1, value2, "identityUrl");
            return (Criteria) this;
        }

        public Criteria andNetworkUrlIsNull() {
            addCriterion("network_url is null");
            return (Criteria) this;
        }

        public Criteria andNetworkUrlIsNotNull() {
            addCriterion("network_url is not null");
            return (Criteria) this;
        }

        public Criteria andNetworkUrlEqualTo(String value) {
            addCriterion("network_url =", value, "networkUrl");
            return (Criteria) this;
        }

        public Criteria andNetworkUrlNotEqualTo(String value) {
            addCriterion("network_url <>", value, "networkUrl");
            return (Criteria) this;
        }

        public Criteria andNetworkUrlGreaterThan(String value) {
            addCriterion("network_url >", value, "networkUrl");
            return (Criteria) this;
        }

        public Criteria andNetworkUrlGreaterThanOrEqualTo(String value) {
            addCriterion("network_url >=", value, "networkUrl");
            return (Criteria) this;
        }

        public Criteria andNetworkUrlLessThan(String value) {
            addCriterion("network_url <", value, "networkUrl");
            return (Criteria) this;
        }

        public Criteria andNetworkUrlLessThanOrEqualTo(String value) {
            addCriterion("network_url <=", value, "networkUrl");
            return (Criteria) this;
        }

        public Criteria andNetworkUrlLike(String value) {
            addCriterion("network_url like", value, "networkUrl");
            return (Criteria) this;
        }

        public Criteria andNetworkUrlNotLike(String value) {
            addCriterion("network_url not like", value, "networkUrl");
            return (Criteria) this;
        }

        public Criteria andNetworkUrlIn(List<String> values) {
            addCriterion("network_url in", values, "networkUrl");
            return (Criteria) this;
        }

        public Criteria andNetworkUrlNotIn(List<String> values) {
            addCriterion("network_url not in", values, "networkUrl");
            return (Criteria) this;
        }

        public Criteria andNetworkUrlBetween(String value1, String value2) {
            addCriterion("network_url between", value1, value2, "networkUrl");
            return (Criteria) this;
        }

        public Criteria andNetworkUrlNotBetween(String value1, String value2) {
            addCriterion("network_url not between", value1, value2, "networkUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlIsNull() {
            addCriterion("image_url is null");
            return (Criteria) this;
        }

        public Criteria andImageUrlIsNotNull() {
            addCriterion("image_url is not null");
            return (Criteria) this;
        }

        public Criteria andImageUrlEqualTo(String value) {
            addCriterion("image_url =", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotEqualTo(String value) {
            addCriterion("image_url <>", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlGreaterThan(String value) {
            addCriterion("image_url >", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlGreaterThanOrEqualTo(String value) {
            addCriterion("image_url >=", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlLessThan(String value) {
            addCriterion("image_url <", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlLessThanOrEqualTo(String value) {
            addCriterion("image_url <=", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlLike(String value) {
            addCriterion("image_url like", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotLike(String value) {
            addCriterion("image_url not like", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlIn(List<String> values) {
            addCriterion("image_url in", values, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotIn(List<String> values) {
            addCriterion("image_url not in", values, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlBetween(String value1, String value2) {
            addCriterion("image_url between", value1, value2, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotBetween(String value1, String value2) {
            addCriterion("image_url not between", value1, value2, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("user_name is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("user_name =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("user_name <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("user_name >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_name >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("user_name <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("user_name <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("user_name like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("user_name not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("user_name in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("user_name not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("user_name between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("user_name not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNull() {
            addCriterion("project_id is null");
            return (Criteria) this;
        }

        public Criteria andProjectIdIsNotNull() {
            addCriterion("project_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjectIdEqualTo(String value) {
            addCriterion("project_id =", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotEqualTo(String value) {
            addCriterion("project_id <>", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThan(String value) {
            addCriterion("project_id >", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdGreaterThanOrEqualTo(String value) {
            addCriterion("project_id >=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThan(String value) {
            addCriterion("project_id <", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLessThanOrEqualTo(String value) {
            addCriterion("project_id <=", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdLike(String value) {
            addCriterion("project_id like", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotLike(String value) {
            addCriterion("project_id not like", value, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdIn(List<String> values) {
            addCriterion("project_id in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotIn(List<String> values) {
            addCriterion("project_id not in", values, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdBetween(String value1, String value2) {
            addCriterion("project_id between", value1, value2, "projectId");
            return (Criteria) this;
        }

        public Criteria andProjectIdNotBetween(String value1, String value2) {
            addCriterion("project_id not between", value1, value2, "projectId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}