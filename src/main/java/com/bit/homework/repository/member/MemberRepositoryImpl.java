package com.bit.homework.repository.member;

import com.bit.homework.config.SqlSessionManager;
import com.bit.homework.domain.Member;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


public class MemberRepositoryImpl implements MemberRepository {

    private static final MemberRepositoryImpl instance = new MemberRepositoryImpl();
    private final SqlSessionFactory sessionFactory = SqlSessionManager.getSqlSessionFactory();

    public static MemberRepositoryImpl getInstance() {
        return instance;
    }

    private MemberRepositoryImpl() {
    }

    @Override
    public void save(Member member) {
        try (SqlSession sqlSession = sessionFactory.openSession()) {
            MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
            mapper.save(member);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Member findById(Integer memberId) {
        Member member = null;
        try (SqlSession sqlSession = sessionFactory.openSession()) {
            MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
            member = mapper.findById(memberId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return member;
    }

    @Override
    public Member findByEmail(String email) {
        Member member = null;
        try (SqlSession sqlSession = sessionFactory.openSession()) {
            MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
            member = mapper.findByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return member;
    }

    @Override
    public void addBookmark(Integer memberId, Integer boardId) {
        try (SqlSession sqlSession = sessionFactory.openSession()) {
            MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
            mapper.addBookmark(memberId, boardId);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeBookmark(Integer memberId, Integer boardId) {
        try (SqlSession sqlSession = sessionFactory.openSession()) {
            MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
            mapper.removeBookmark(memberId, boardId);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addBoardLikes(Integer memberId, Integer boardId) {
        try (SqlSession sqlSession = sessionFactory.openSession()) {
            MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
            mapper.addBoardLikes(memberId, boardId);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeBoardLikes(Integer memberId, Integer boardId) {
        try (SqlSession sqlSession = sessionFactory.openSession()) {
            MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
            mapper.removeBoardLikes(memberId, boardId);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addBoardDislikes(Integer memberId, Integer boardId) {
        try (SqlSession sqlSession = sessionFactory.openSession()) {
            MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
            mapper.addBoardDislikes(memberId, boardId);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeBoardDislikes(Integer memberId, Integer boardId) {
        try (SqlSession sqlSession = sessionFactory.openSession()) {
            MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
            mapper.removeBoardDislikes(memberId, boardId);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean existsBookmark(Integer memberId, Integer boardId) {
        boolean exists = false;
        try (SqlSession sqlSession = sessionFactory.openSession()) {
            MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
            exists = mapper.existsBookmark(memberId, boardId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return exists;
    }

    @Override
    public boolean existsBoardLikes(Integer memberId, Integer boardId) {
        boolean exists = false;
        try (SqlSession sqlSession = sessionFactory.openSession()) {
            MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
            exists = mapper.existsBoardLikes(memberId, boardId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return exists;
    }

    @Override
    public boolean existsBoardDislikes(Integer memberId, Integer boardId) {
        boolean exists = false;
        try (SqlSession sqlSession = sessionFactory.openSession()) {
            MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);
            exists = mapper.existsBoardDislikes(memberId, boardId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return exists;
    }

}
