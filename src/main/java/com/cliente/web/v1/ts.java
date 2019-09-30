//@RepositoryRestController
//public class BookCustomRepository {
//  private final DomainObjectReader domainObjectReader;
//  private final ObjectMapper mapper;
//  private final BookRepository repository;
//
//  @Autowired
//  public BookCustomRepository(BookRepository bookRepository, ObjectMapper mapper,
//      PersistentEntities persistentEntities, Associations associationLinks) {
//    this.repository = bookRepository;
//    this.mapper = mapper;
//    this.domainObjectReader = new DomainObjectReader(persistentEntities, associationLinks);
//  }
//
//  @PatchMapping(value = "/book/{id}",
//      consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_JSON_VALUE})
//  public ResponseEntity<?> patch(@PathVariable String id, ServletServerHttpRequest request)
//      throws IOException {
//    Book entityToPatch = repository.findById(id).orElseThrow(ResourceNotFoundException::new);
//    Book patched = domainObjectReader.read(request.getBody(), entityToPatch, mapper);
//    repository.save(patched);
//    return ResponseEntity.noContent().build();
//  }
//}
