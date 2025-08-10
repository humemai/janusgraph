:remote connect tinkerpop.server conf/remote.yaml session
:remote console

println "=== Creating complex schema ==="
mgmt = graph.openManagement();

// Property keys (idempotent, single-line)
if (mgmt.getPropertyKey('name')==null) mgmt.makePropertyKey('name').dataType(String.class).cardinality(Cardinality.SINGLE).make();
if (mgmt.getPropertyKey('email')==null) mgmt.makePropertyKey('email').dataType(String.class).cardinality(Cardinality.SINGLE).make();
if (mgmt.getPropertyKey('age')==null) mgmt.makePropertyKey('age').dataType(Integer.class).cardinality(Cardinality.SINGLE).make();
if (mgmt.getPropertyKey('salary')==null) mgmt.makePropertyKey('salary').dataType(Integer.class).cardinality(Cardinality.SINGLE).make();
if (mgmt.getPropertyKey('experience_years')==null) mgmt.makePropertyKey('experience_years').dataType(Integer.class).cardinality(Cardinality.SINGLE).make();
if (mgmt.getPropertyKey('bio')==null) mgmt.makePropertyKey('bio').dataType(String.class).cardinality(Cardinality.SINGLE).make();
if (mgmt.getPropertyKey('company_type')==null) mgmt.makePropertyKey('company_type').dataType(String.class).cardinality(Cardinality.SINGLE).make();
if (mgmt.getPropertyKey('founded')==null) mgmt.makePropertyKey('founded').dataType(Integer.class).cardinality(Cardinality.SINGLE).make();
if (mgmt.getPropertyKey('employees')==null) mgmt.makePropertyKey('employees').dataType(Integer.class).cardinality(Cardinality.SINGLE).make();
if (mgmt.getPropertyKey('revenue')==null) mgmt.makePropertyKey('revenue').dataType(Long.class).cardinality(Cardinality.SINGLE).make();
if (mgmt.getPropertyKey('industry')==null) mgmt.makePropertyKey('industry').dataType(String.class).cardinality(Cardinality.SINGLE).make();
if (mgmt.getPropertyKey('city')==null) mgmt.makePropertyKey('city').dataType(String.class).cardinality(Cardinality.SINGLE).make();
if (mgmt.getPropertyKey('country')==null) mgmt.makePropertyKey('country').dataType(String.class).cardinality(Cardinality.SINGLE).make();
if (mgmt.getPropertyKey('population')==null) mgmt.makePropertyKey('population').dataType(Integer.class).cardinality(Cardinality.SINGLE).make();
if (mgmt.getPropertyKey('ranking')==null) mgmt.makePropertyKey('ranking').dataType(Integer.class).cardinality(Cardinality.SINGLE).make();
if (mgmt.getPropertyKey('established')==null) mgmt.makePropertyKey('established').dataType(Integer.class).cardinality(Cardinality.SINGLE).make();
if (mgmt.getPropertyKey('project_type')==null) mgmt.makePropertyKey('project_type').dataType(String.class).cardinality(Cardinality.SINGLE).make();
if (mgmt.getPropertyKey('budget')==null) mgmt.makePropertyKey('budget').dataType(Integer.class).cardinality(Cardinality.SINGLE).make();
if (mgmt.getPropertyKey('start_date')==null) mgmt.makePropertyKey('start_date').dataType(String.class).cardinality(Cardinality.SINGLE).make();
if (mgmt.getPropertyKey('status')==null) mgmt.makePropertyKey('status').dataType(String.class).cardinality(Cardinality.SINGLE).make();
if (mgmt.getPropertyKey('skill_level')==null) mgmt.makePropertyKey('skill_level').dataType(String.class).cardinality(Cardinality.SINGLE).make();
if (mgmt.getPropertyKey('category')==null) mgmt.makePropertyKey('category').dataType(String.class).cardinality(Cardinality.SINGLE).make();
if (mgmt.getPropertyKey('since')==null) mgmt.makePropertyKey('since').dataType(Integer.class).cardinality(Cardinality.SINGLE).make();
if (mgmt.getPropertyKey('position')==null) mgmt.makePropertyKey('position').dataType(String.class).cardinality(Cardinality.SINGLE).make();
if (mgmt.getPropertyKey('degree')==null) mgmt.makePropertyKey('degree').dataType(String.class).cardinality(Cardinality.SINGLE).make();
if (mgmt.getPropertyKey('graduation_year')==null) mgmt.makePropertyKey('graduation_year').dataType(Integer.class).cardinality(Cardinality.SINGLE).make();
if (mgmt.getPropertyKey('friendship_strength')==null) mgmt.makePropertyKey('friendship_strength').dataType(String.class).cardinality(Cardinality.SINGLE).make();
if (mgmt.getPropertyKey('role_in_project')==null) mgmt.makePropertyKey('role_in_project').dataType(String.class).cardinality(Cardinality.SINGLE).make();
if (mgmt.getPropertyKey('years_of_experience')==null) mgmt.makePropertyKey('years_of_experience').dataType(Integer.class).cardinality(Cardinality.SINGLE).make();

// Vertex labels (idempotent)
if (mgmt.getVertexLabel('person')==null) mgmt.makeVertexLabel('person').make(); if (mgmt.getVertexLabel('company')==null) mgmt.makeVertexLabel('company').make(); if (mgmt.getVertexLabel('university')==null) mgmt.makeVertexLabel('university').make(); if (mgmt.getVertexLabel('project')==null) mgmt.makeVertexLabel('project').make(); if (mgmt.getVertexLabel('skill')==null) mgmt.makeVertexLabel('skill').make(); if (mgmt.getVertexLabel('location')==null) mgmt.makeVertexLabel('location').make();

// Edge labels (idempotent)
if (mgmt.getEdgeLabel('knows')==null) mgmt.makeEdgeLabel('knows').make(); if (mgmt.getEdgeLabel('works_at')==null) mgmt.makeEdgeLabel('works_at').make(); if (mgmt.getEdgeLabel('studied_at')==null) mgmt.makeEdgeLabel('studied_at').make(); if (mgmt.getEdgeLabel('friends_with')==null) mgmt.makeEdgeLabel('friends_with').make(); if (mgmt.getEdgeLabel('collaborates_on')==null) mgmt.makeEdgeLabel('collaborates_on').make(); if (mgmt.getEdgeLabel('has_skill')==null) mgmt.makeEdgeLabel('has_skill').make(); if (mgmt.getEdgeLabel('located_in')==null) mgmt.makeEdgeLabel('located_in').make(); if (mgmt.getEdgeLabel('manages')==null) mgmt.makeEdgeLabel('manages').make(); if (mgmt.getEdgeLabel('mentors')==null) mgmt.makeEdgeLabel('mentors').make();

// Composite indexes (create if missing)
if (!mgmt.getGraphIndexes(Vertex.class).any{ it.name()=='personByEmail' }) mgmt.buildIndex('personByEmail', Vertex.class).addKey(mgmt.getPropertyKey('email')).indexOnly(mgmt.getVertexLabel('person')).unique().buildCompositeIndex();
if (!mgmt.getGraphIndexes(Vertex.class).any{ it.name()=='personByName' }) mgmt.buildIndex('personByName', Vertex.class).addKey(mgmt.getPropertyKey('name')).indexOnly(mgmt.getVertexLabel('person')).buildCompositeIndex();
if (!mgmt.getGraphIndexes(Vertex.class).any{ it.name()=='companyByName' }) mgmt.buildIndex('companyByName', Vertex.class).addKey(mgmt.getPropertyKey('name')).indexOnly(mgmt.getVertexLabel('company')).buildCompositeIndex();
if (!mgmt.getGraphIndexes(Vertex.class).any{ it.name()=='universityByName' }) mgmt.buildIndex('universityByName', Vertex.class).addKey(mgmt.getPropertyKey('name')).indexOnly(mgmt.getVertexLabel('university')).buildCompositeIndex();
if (!mgmt.getGraphIndexes(Vertex.class).any{ it.name()=='locationByCity' }) mgmt.buildIndex('locationByCity', Vertex.class).addKey(mgmt.getPropertyKey('city')).indexOnly(mgmt.getVertexLabel('location')).buildCompositeIndex();

// Mixed indexes (OpenSearch via alias 'search')
if (!mgmt.getGraphIndexes(Vertex.class).any{ it.name()=='personMixed' }) mgmt.buildIndex('personMixed', Vertex.class).addKey(mgmt.getPropertyKey('name')).addKey(mgmt.getPropertyKey('age')).addKey(mgmt.getPropertyKey('salary')).addKey(mgmt.getPropertyKey('experience_years')).addKey(mgmt.getPropertyKey('bio')).addKey(mgmt.getPropertyKey('city')).addKey(mgmt.getPropertyKey('email')).indexOnly(mgmt.getVertexLabel('person')).buildMixedIndex('search');
if (!mgmt.getGraphIndexes(Vertex.class).any{ it.name()=='companyMixed' }) mgmt.buildIndex('companyMixed', Vertex.class).addKey(mgmt.getPropertyKey('name')).addKey(mgmt.getPropertyKey('founded')).addKey(mgmt.getPropertyKey('employees')).addKey(mgmt.getPropertyKey('revenue')).addKey(mgmt.getPropertyKey('industry')).addKey(mgmt.getPropertyKey('company_type')).indexOnly(mgmt.getVertexLabel('company')).buildMixedIndex('search');
if (!mgmt.getGraphIndexes(Vertex.class).any{ it.name()=='projectMixed' }) mgmt.buildIndex('projectMixed', Vertex.class).addKey(mgmt.getPropertyKey('name')).addKey(mgmt.getPropertyKey('project_type')).addKey(mgmt.getPropertyKey('budget')).addKey(mgmt.getPropertyKey('status')).indexOnly(mgmt.getVertexLabel('project')).buildMixedIndex('search');
if (!mgmt.getGraphIndexes(Vertex.class).any{ it.name()=='locationMixed' }) mgmt.buildIndex('locationMixed', Vertex.class).addKey(mgmt.getPropertyKey('city')).addKey(mgmt.getPropertyKey('country')).addKey(mgmt.getPropertyKey('population')).indexOnly(mgmt.getVertexLabel('location')).buildMixedIndex('search');

// Edge indexes (safe create)
try { mgmt.buildEdgeIndex(mgmt.getEdgeLabel('works_at'), 'worksByPosition', Direction.BOTH, Order.desc, mgmt.getPropertyKey('position')) } catch(e) { }
try { mgmt.buildEdgeIndex(mgmt.getEdgeLabel('knows'), 'knowsBySince', Direction.BOTH, Order.desc, mgmt.getPropertyKey('since')) } catch(e) { }

mgmt.commit(); println "✓ Complex schema created"
