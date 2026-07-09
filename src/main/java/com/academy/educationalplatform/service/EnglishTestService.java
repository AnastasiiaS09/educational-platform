public class EnglishTestService {
    private final EnglishTestRepository englishTestRepository;

    public EnglishTestService(EnglishTestRepository englishTestRepository) {
        this.englishTestRepository = englishTestRepository;
    }

    public EnglishTest addTest(String name, String description) {
        try {
            if (englishTestRepository.existsByName(name)) {
                throw PlatformException.of(PlatformErrorCode.TEST_ALREADY_EXISTS);
            }

            EnglishTest englishTest = new EnglishTest();
            englishTest.setName(name);
            englishTest.setDescription(description);

            return englishTestRepository.save(englishTest);
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public EnglishTest findById(Long id) {
        return englishTestRepository.findById(id).orElseThrow(() -> {
            throw PlatformException.of(PlatformErrorCode.TEST_NOT_FOUND);
        });
    }

    public EnglishTest update(Long id, String description, String name) {
        try {
            EnglishTest englishTest = englishTestRepository.findById(id).orElseThrow(() ->
                    PlatformException.of(PlatformErrorCode.TEST_NOT_FOUND));
            englishTest.setDescription(description);
            englishTest.setName(name);

            return englishTest;
        } catch (RuntimeException e) {
            throw e;
        }
    }

    public void deleteTest(Long id) {
        try {
            englishTestRepository.deleteById(id);
            System.out.println("Test was deleted successfully");
        } catch (RuntimeException e) {
            throw e;
        }
    }
}