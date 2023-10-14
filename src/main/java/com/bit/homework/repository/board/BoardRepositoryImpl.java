package com.bit.homework.repository.board;

import com.bit.homework.config.SqlSessionManager;
import com.bit.homework.domain.Board;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardRepositoryImpl implements BoardRepository {

    private static final BoardRepositoryImpl instance = new BoardRepositoryImpl();

    private final SqlSessionFactory sessionFactory = SqlSessionManager.getSqlSessionFactory();

    public static BoardRepositoryImpl getInstance() {
        return instance;
    }

    private BoardRepositoryImpl() {
    }

    @Override
    public void save(Board board) {
        try (SqlSession sqlSession = sessionFactory.openSession()) {
            BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
            mapper.save(board);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Integer boardId) {
        try (SqlSession sqlSession = sessionFactory.openSession()) {
            BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
            mapper.delete(boardId);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Integer boardId, Board board) {
        try (SqlSession sqlSession = sessionFactory.openSession()) {
            BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
            mapper.update(boardId, board);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Board findById(Integer boardId) {
        Board board = null;
        try (SqlSession sqlSession = sessionFactory.openSession()) {
            BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
            board = mapper.findById(boardId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return board;
    }

    @Override
    public List<Board> findAll(Map<String, Object> params) {
        List<Board> boards = new ArrayList<>();
        try (SqlSession sqlSession = sessionFactory.openSession()) {
            BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
            boards = mapper.findAll(params);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return boards;
    }

    @Override
    public int getTotalCount(Map<String, Object> params) {
        int count = 0;
        try (SqlSession sqlSession = sessionFactory.openSession()) {
            BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
            count = mapper.getTotalCount(params);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    @Override
    public void increaseViews(Integer boardId) {
        try (SqlSession sqlSession = sessionFactory.openSession()) {
            BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
            mapper.increaseViews(boardId);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addLikes(Integer boardId) {
        try (SqlSession sqlSession = sessionFactory.openSession()) {
            BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
            mapper.addLikes(boardId);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cancelLikes(Integer boardId) {
        try (SqlSession sqlSession = sessionFactory.openSession()) {
            BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
            mapper.cancelLikes(boardId);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addDislikes(Integer boardId) {
        try (SqlSession sqlSession = sessionFactory.openSession()) {
            BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
            mapper.addDislikes(boardId);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cancelDislikes(Integer boardId) {
        try (SqlSession sqlSession = sessionFactory.openSession()) {
            BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
            mapper.cancelDislikes(boardId);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Board> findAllByMemberBookmark(Integer memberId) {
        List<Board> boards = new ArrayList<>();
        try (SqlSession sqlSession = sessionFactory.openSession()) {
            BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
            boards = mapper.findAllByMemberBookmark(memberId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return boards;
    }

    @Override
    public int getLikesCount(Integer boardId) {
        int count = 0;
        try (SqlSession sqlSession = sessionFactory.openSession()) {
            BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
            count = mapper.getLikesCount(boardId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }

    @Override
    public int getDislikesCount(Integer boardId) {
        int count = 0;
        try (SqlSession sqlSession = sessionFactory.openSession()) {
            BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
            count = mapper.getDislikesCount(boardId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }


}
